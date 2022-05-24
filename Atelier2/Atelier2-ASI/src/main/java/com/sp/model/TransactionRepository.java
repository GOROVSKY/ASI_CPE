package com.sp.model;

import java.util.List;

import com.sp.entity.Transaction;

import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.
public interface TransactionRepository extends CrudRepository<Transaction, Long> { // Long: Type of Employee ID.

	 Transaction findById(int id);
	

    List<Transaction> findBycardIdLike(String cardId);
    List<Transaction> findBysellerIdLike(String sellerId);

}