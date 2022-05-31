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
import com.sp.model.TransactionRepository;



@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	private String urlUserCard ="http://localhost:8081/users/?/inventory";
	
	private final RestTemplate restTemplate;

	public TransactionService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void addTransaction(Transaction transaction) {
		
		URL url;
		try {
			System.out.println("url: " + urlUserCard.replace('?', (char)(transaction.getSellerId()+'0')));

			UserCardDTO u = this.restTemplate.getForObject(urlUserCard.replace('?', (char)(transaction.getSellerId()+'0')), UserCardDTO.class);

			System.out.println("u: " + u);
			
			//UserCard u = userCardRepository.findByIdCardIdAndIdUserId(transaction.getCardId(),transaction.getSellerId());
			if(u.getQuantity()>0)
			{
				u.setQuantity(u.getQuantity()-1);
				this.restTemplate.postForObject(urlUserCard,u, UserCardDTO.class);
				transactionRepository.save(transaction);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		/*UserCard u = userCardRepository.findByIdCardIdAndIdUserId(t.getCardId(),t.getBuyerId());
		if(u == null)
		{
			u = new UserCard(new UserCardId(t.getCardId(),t.getBuyerId()),1);
		}
		else {
			u.setQuantity(u.getQuantity()+1);
		}
		//TODO CHECK WALLET BEFORE BUYING RETURN ERROR IF WALLET IS NOT ENOUGH
		Card card = cardRepository.findById(t.getCardId());
		Users buyer = userRepository.findById(transaction.getBuyerId());
		Users seller = userRepository.findById(t.getSellerId());
		buyer.setWallet(buyer.getWallet()-card.getPrice().intValue());
		seller.setWallet(seller.getWallet()+card.getPrice().intValue());
		userRepository.save(buyer);
		userRepository.save(seller);
		userCardRepository.save(u);
		transactionRepository.save(t);*/
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
}
