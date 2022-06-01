package com.sp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sp.dto.CardDTO;
import com.sp.dto.CardTagDTO;
import com.sp.entity.Card;
import com.sp.model.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardService {
	private String urlTag = "http://127.0.0.1:5000/api/v1/resources/photo?url="	;
	@Autowired
	CardRepository cardRepository ;
	
	private final RestTemplate restTemplate;

	public CardService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	public void addCard(Card card) {
		
		List<String> cardtag = this.restTemplate.getForObject(urlTag + card.getImageUrl(), List.class);
		cardtag.size();
		System.out.println(cardtag);
		System.out.println(cardtag.size());
		cardRepository.save(card);
	}
	
	public List<Card> getCards() {
		return cardRepository.findByOrderByIdAsc();
//		List<Card> result = 
//				  StreamSupport.stream(cardRepository.findAll().spliterator(), false)
//				    .collect(Collectors.toList());

	}
	
	public Card findCardById(Integer id) {
		return cardRepository.findById(id);
	}

	public void updateCard(Card card) {
		Card cardDB = cardRepository.findById(card.getId());
		cardRepository.save(cardDB);
	}
	
	public void deleteCard(Card card) {
		cardRepository.delete(card);
	}
}
