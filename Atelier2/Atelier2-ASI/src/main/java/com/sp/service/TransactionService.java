package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entity.Card;
import com.sp.entity.Transaction;
import com.sp.model.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public void addTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}
	
	public List<Transaction> getTransaction() {
		List<Transaction> result = 
				  StreamSupport.stream(transactionRepository.findAll().spliterator(), false)
				    .collect(Collectors.toList());
		return result;
	}

	public void updateTransaction(Transaction transaction) {
		Transaction t = transactionRepository.findById(transaction.getId());
		t.setBuyerId(transaction.getBuyerId());
		t.setDateBuy(transaction.getDateBuy());
		transactionRepository.save(t);
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
}
