package com.sp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
		List<Transaction> transaction = new ArrayList<Transaction>();
		return transaction;
	}

	public void updateTransaction(Transaction transaction) {
		//TODO
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactionRepository.delete(transaction);
	}
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}
}
