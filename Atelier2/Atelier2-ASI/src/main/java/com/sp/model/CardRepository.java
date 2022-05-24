package com.sp.model;

import java.util.List;

import com.sp.entity.Card;

import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.
public interface CardRepository extends CrudRepository<Card, Long> { // Long: Type of Card ID.

	Card findById(int id);

    List<Card> findByNameLike(String name);

}