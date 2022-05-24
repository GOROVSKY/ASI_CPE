package com.sp.controller;

import com.sp.entity.Card;
import com.sp.service.CardService;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardCrt {

	@Autowired
	CardService cardService;

	@RequestMapping(value = { "/cards" }, method = RequestMethod.GET)
	public List<Card> getCard() {
		List<Card> list = cardService.getCards();
		return list;
	}

	@RequestMapping(value = { "/cards/{cardId}" }, method = RequestMethod.GET)
	public Card getCardById(@PathVariable @NotNull @DecimalMin("0") Integer cardId) {
		Card u = cardService.findCardById(cardId);
		return u;
	}

	@RequestMapping(value = { "/cards" }, method = RequestMethod.POST)
	public String addCard(@RequestBody Card card) {
		cardService.addCard(card);
		return card.toString();
	}

	@RequestMapping(value = { "/cards" }, method = RequestMethod.PUT)
	public void updateCard(Card card) {
		cardService.addCard(card);
	}

	@RequestMapping(value = { "/cards" }, method = RequestMethod.DELETE)
	public void deleteCard(@RequestBody Card card) {
		cardService.deleteCard(card);
	}
}