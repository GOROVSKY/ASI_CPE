package com.sp.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.sp.dto.CardDTO;
import com.sp.entity.Card;
import com.sp.service.CardService;

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
	public List<CardDTO> getCard() {
		
		List<CardDTO> dtoList = new ArrayList<CardDTO>();
		for (Card card : cardService.getCards()) {
			dtoList.add(new CardDTO(card.getId(), card.getName(), card.getDescription(), card.getFamily(), card.getAffinity(), card.getAttack(),
					card.getDefense(), card.getHp(), card.getEnergy(), card.getPrice(), card.getImageUrl()));
		}
		
		return dtoList;
	}

	@RequestMapping(value = { "/cards/{cardId}" }, method = RequestMethod.GET)
	public CardDTO getCardById(@PathVariable @NotNull @DecimalMin("0") Integer cardId) {
		Card card = cardService.findCardById(cardId);
		CardDTO dto = new CardDTO(card.getId(), card.getName(), card.getDescription(), card.getFamily(), card.getAffinity(), card.getAttack(),
				card.getDefense(), card.getHp(), card.getEnergy(), card.getPrice(), card.getImageUrl());
		return dto;
	}

	@RequestMapping(value = { "/cards" }, method = RequestMethod.POST)
	public CardDTO addCard(@RequestBody Card card) {
		cardService.addCard(card);
		return new CardDTO(card.getId(), card.getName(), card.getDescription(), card.getFamily(), card.getAffinity(), card.getAttack(),
				card.getDefense(), card.getHp(), card.getEnergy(), card.getPrice(), card.getImageUrl());
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