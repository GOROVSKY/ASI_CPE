package com.sp.controller;


import com.sp.entity.Card;
import com.sp.service.CardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardCrt {
	
	@Autowired
    CardService cardService;

	@RequestMapping(value = { "/cards" }, method = RequestMethod.GET)
	public List<Card> getCard(Model model) {
		List<Card> list = cardService.getCards();
		return list;
	}

	@RequestMapping(value = { "/cards" }, method = RequestMethod.PUT)
	public int addCard(Model model, Card card) {
		int id = cardService.addCard(card);
		return id;
	}
	
	@RequestMapping(value = { "/cards" }, method = RequestMethod.POST)
	public void updateCard(Model model, Card card) {
		cardService.addCard(card);
	}
	
	@RequestMapping(value = { "/cards" }, method = RequestMethod.DELETE)
	public void deleteCard(Model model, Card card) {
		cardService.deleteCard(card);
	}
}