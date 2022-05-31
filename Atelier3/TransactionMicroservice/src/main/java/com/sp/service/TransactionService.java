package com.sp.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter; 
import com.sp.entity.Transaction;
import com.sp.dto.UserCardDTO;
import com.sp.dto.UsersDTO;
import com.sp.dto.CardDTO;

import com.sp.model.TransactionRepository;



@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	private String getUrlUserCard ="http://localhost:8081/users/{userId}/inventory/{cardId}";
	private String putUrlUserCard ="http://localhost:8081/users/{userId}/inventory";
	private String urlUser ="http://localhost:8081/users/{userId}";
	private String urlCard ="http://localhost:8082/cards/{cardId}";



	private final RestTemplate restTemplate;

	public TransactionService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void addTransaction(Transaction transaction) {
		
		String urlM = getUrlUserCard.replace("{userId}", Integer.toString(transaction.getSellerId()));
		urlM = urlM.replace("{cardId}", Integer.toString(transaction.getCardId()));
		System.out.println(urlM);
		UserCardDTO u = this.restTemplate.getForObject(urlM, UserCardDTO.class);
		
		if(u == null)
		{
			System.out.println("UserCardList nulle, l'utilisateur ne posede pas la carte");
		}
		else {
			//u = userCardList[0];
			u.setQuantity(u.getQuantity()-1);
			urlM = putUrlUserCard.replace("{userId}", Integer.toString(transaction.getSellerId()));
			System.out.println(urlM);
			this.restTemplate.put(urlM,u, UserCardDTO.class);
			transactionRepository.save(transaction);
		}
		
	}
	
	public List<Transaction> getTransaction() {
		List<Transaction> result = 
				  StreamSupport.stream(transactionRepository.findByBuyerIdIsNull().spliterator(), false)
				    .collect(Collectors.toList());
		return result;
	}

	public void updateTransaction(Transaction transaction) {
		Transaction t = transactionRepository.findById(transaction.getId());
		t.setBuyerId(transaction.getBuyerId());
		t.setDateBuy(transaction.getDateBuy());
		
		String urlM = getUrlUserCard.replace("{userId}", Integer.toString(transaction.getSellerId()));
		urlM = urlM.replace("{cardId}", Integer.toString(transaction.getCardId()));
		System.out.println(urlM);
		UserCardDTO u = this.restTemplate.getForObject(urlM, UserCardDTO.class);
		
		urlM = putUrlUserCard.replace("{userId}", Integer.toString(transaction.getBuyerId()));
		if(u == null)
		{
			u = new UserCardDTO(t.getCardId(),t.getBuyerId(),1,100);
			this.restTemplate.postForObject(urlM, u, UserCardDTO.class);
		}
		else {
			u.setQuantity(u.getQuantity()+1);
			this.restTemplate.put(urlM, u, UserCardDTO.class);
		}
		
		urlM = urlCard.replace("{cardId}", Integer.toString(transaction.getCardId()));
		CardDTO card = this.restTemplate.getForObject(urlM, CardDTO.class);
		
		urlM = urlUser.replace("{userId}", Integer.toString(transaction.getBuyerId()));
		UsersDTO buyer = this.restTemplate.getForObject(urlM, UsersDTO.class);
		buyer.setWallet(buyer.getWallet()-card.getPrice().intValue());
		
		urlM = urlUser.replace("{userId}", Integer.toString(transaction.getSellerId()));
		UsersDTO seller = this.restTemplate.getForObject(urlM, UsersDTO.class);
		buyer.setWallet(buyer.getWallet()+card.getPrice().intValue());

		urlM = putUrlUserCard.replace("{userId}", Integer.toString(transaction.getBuyerId()));
		this.restTemplate.put(urlUser, buyer);
		this.restTemplate.put(urlUser, seller);
		

		transactionRepository.save(t);
		//TODO CHECK WALLET BEFORE BUYING RETURN ERROR IF WALLET IS NOT ENOUGH
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
}
