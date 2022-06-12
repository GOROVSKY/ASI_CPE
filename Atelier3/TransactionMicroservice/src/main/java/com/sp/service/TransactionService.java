package com.sp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sp.dto.CardDTO;
import com.sp.dto.UserCardDTO;
import com.sp.dto.UsersDTO;
import com.sp.entity.Transaction;
import com.sp.model.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	private String getUrlUserCard ="http://localhost:80/api/users/{userId}/inventory/{cardId}";
	private String putUrlUserCard ="http://localhost:80/api/users/{userId}/inventory";
	private String urlUserId ="http://localhost:80/api/users/{userId}";
	private String urlUser ="http://localhost:80/api/users";
	private String urlCard ="http://localhost:80/api/cards/{cardId}";



	private final RestTemplate restTemplate;

	public TransactionService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void addTransaction(Transaction transaction) {
		
		String urlM = getUrlUserCard.replace("{userId}", Integer.toString(transaction.getSellerId()));
		urlM = urlM.replace("{cardId}", Integer.toString(transaction.getCardId()));
		UserCardDTO u = this.restTemplate.getForObject(urlM, UserCardDTO.class);
		
		if(u == null)
		{
			System.out.println("UserCardList nulle, l'utilisateur ne posede pas la carte");
		}
		else {
			//u = userCardList[0];
			u.setQuantity(u.getQuantity()-1);
			urlM = putUrlUserCard.replace("{userId}", Integer.toString(transaction.getSellerId()));
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
		
		String urlM = getUrlUserCard.replace("{userId}", Integer.toString(t.getSellerId()));
		urlM = urlM.replace("{cardId}", Integer.toString(t.getCardId()));
		UserCardDTO u = this.restTemplate.getForObject(urlM, UserCardDTO.class);
		
		urlM = putUrlUserCard.replace("{userId}", Integer.toString(t.getBuyerId()));
		if(u == null)
		{
			u = new UserCardDTO(t.getCardId(),t.getBuyerId(),1,100);
			this.restTemplate.postForObject(urlM, u, UserCardDTO.class);
		}
		else {
			u.setQuantity(u.getQuantity()+1);
			this.restTemplate.put(urlM, u, UserCardDTO.class);
		}
		
		urlM = urlCard.replace("{cardId}", Integer.toString(t.getCardId()));
		CardDTO card = this.restTemplate.getForObject(urlM, CardDTO.class);
		
		urlM = urlUserId.replace("{userId}", Integer.toString(t.getBuyerId()));
		UsersDTO buyer = this.restTemplate.getForObject(urlM, UsersDTO.class);
		buyer.setWallet(buyer.getWallet()-card.getPrice().intValue());
		
		urlM = urlUserId.replace("{userId}", Integer.toString(t.getSellerId()));
		UsersDTO seller = this.restTemplate.getForObject(urlM, UsersDTO.class);
		seller.setWallet(buyer.getWallet()+card.getPrice().intValue());


		this.restTemplate.put(urlUser, buyer, UsersDTO.class);
		this.restTemplate.put(urlUser, seller, UsersDTO.class);
		

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
