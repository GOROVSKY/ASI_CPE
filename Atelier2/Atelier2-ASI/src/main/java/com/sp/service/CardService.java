package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entity.Card;
import com.sp.entity.Users;
import com.sp.model.CardRepository;

@Service
public class CardService {
		
	@Autowired
	CardRepository cardRepository ;

	public int addCard(Card card) {
		cardRepository.save(card);
		return 1;
	}
	
	public List<Card> getCards() {
		List<Card> result = 
				  StreamSupport.stream(cardRepository.findAll().spliterator(), false)
				    .collect(Collectors.toList());
		return result;
	}

	public void updateCard(Card card) {
		//TODO
	}
	
	public void deleteCard(Card card) {
		cardRepository.delete(card);
	}
}
