package com.sp.model;

import java.util.List;



import org.springframework.data.repository.CrudRepository;

import com.sp.entity.Card;
import com.sp.entity.CardTag;

// This is an Interface.
// No need Annotation here.

public interface CardTagRepository extends CrudRepository<CardTag, Long> {
	

}