  package com.sp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.sp.dto.TransactionDTO;

import com.sp.entity.Transaction;
import com.sp.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
  
  @RestController
  public class TransactionCrt {
	  
	@Autowired
	TransactionService transactionService;

  	@RequestMapping(value = { "/transactions"}, method = RequestMethod.GET)
    public List<TransactionDTO> transaction() {
  		List<TransactionDTO> dtoList = new ArrayList<TransactionDTO>();
  		for(Transaction transaction :transactionService.getTransaction() ) {
  			dtoList.add(new TransactionDTO(transaction.getCardId(),  transaction.getBuyerId(), transaction.getSellerId()  ));
  		}
  		return dtoList;
    	 
    }

  @RequestMapping(value = { "/transactions"}, method = RequestMethod.POST)
    public String addTransaction(@RequestBody Transaction transaction ) {
	  	
	  transactionService.addTransaction(transaction);
	  return transaction.toString();
  	}
  
  @RequestMapping(value = { "/transactions"}, method = RequestMethod.PUT)
  public String updateTransaction(@RequestBody Transaction transaction ) {
	  	
	  transactionService.updateTransaction(transaction);
	  return transaction.toString();
	}
  
  @RequestMapping(value = { "/transactions/{transactionId}"}, method = RequestMethod.GET)
  public Transaction getTransactionById(@PathVariable @NotNull @DecimalMin("0") Integer transactionId ) {
	  Transaction u = transactionService.findTransactionById(transactionId);
	  return u;
	}

  	
  }

