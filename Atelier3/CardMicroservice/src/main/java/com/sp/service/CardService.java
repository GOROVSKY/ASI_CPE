package com.sp.service;

import java.util.ArrayList;
import java.util.List;

import com.sp.entity.Card;
import com.sp.entity.CardTag;
import com.sp.model.CardRepository;
import com.sp.model.CardTagRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardService {
	private String urlTag = "http://127.0.0.1:5000/api/v1/resources/photo?url="	;
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	CardTagRepository CardTagRepository;
	
	private final RestTemplate restTemplate;

	public CardService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	public void addCard(Card card) {
		try {
		String cardtag = this.restTemplate.getForObject(urlTag + card.getImageUrl(), String.class);
		JSONObject jsonObject = new JSONObject(cardtag);
		JSONArray listTag = jsonObject.getJSONArray("tags");
		List<String> tags = new ArrayList<String>();
		if (listTag != null) { 
			   int len = listTag.length();
			   for (int i=0;i<len;i++){ 
				   CardTag ct = new CardTag(card.getId(),listTag.get(i).toString());
				   CardTagRepository.save(ct);
			   } 
			} 
		}
		catch(Exception e)
		{
			System.out.println("Connexion impossible avec flask");
		}

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
