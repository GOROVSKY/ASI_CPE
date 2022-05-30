package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entity.Card;
import com.sp.entity.Transaction;
import com.sp.entity.UserCard;
import com.sp.entity.UserCardId;
import com.sp.entity.Users;
import com.sp.model.CardRepository;
import com.sp.model.TransactionRepository;
import com.sp.model.UserCardRepository;
import com.sp.model.UserRepository;


@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	UserCardRepository userCardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	
	public void addTransaction(Transaction transaction) {
		UserCard u = userCardRepository.findByIdCardIdAndIdUserId(transaction.getCardId(),transaction.getSellerId());
		if(u.getQuantity()>0)
		{
			u.setQuantity(u.getQuantity()-1);
			userCardRepository.save(u);
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
		UserCard u = userCardRepository.findByIdCardIdAndIdUserId(t.getCardId(),t.getBuyerId());
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
		transactionRepository.save(t);
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
	
	public List<UserCard> getInventory(Integer userId){
		return userCardRepository.findByIdUserId(userId);
	}
}
