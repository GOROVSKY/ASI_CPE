package com.sp.controller;

import com.sp.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sp.model.Card;

@Service
public class CardDao {
	private List<Card> myCardList;
	private Random randomGenerator;
	private final RestTemplate restTemplate;
	private String url = "https://asi2-backend-market.herokuapp.com/cards";

	public CardDao(RestTemplateBuilder restTemplateBuilder) {
		myCardList=new ArrayList<>();
		randomGenerator = new Random();
		this.restTemplate = restTemplateBuilder.build();
		createCardList();
	}

	private void createCardList() {
		Card[] cards = this.getCardsAsObject();
		for(Card card : cards) {
			myCardList.add(card);
		}
	}
	
	private Card[] getCardsAsObject() {
	    return this.restTemplate.getForObject(url, Card[].class);
	}
	
	
	public List<Card> getCardList() {
		return this.myCardList;
	}
	
	public Card getCardByName(String name){
		for (Card cardBean : myCardList) {
			if(cardBean.getName().equals(name)){
				return cardBean;
			}
		}
		return null;
	}
	
	public Card getRandomCard(){
		int index=randomGenerator.nextInt(this.myCardList.size());
		return this.myCardList.get(index);
	}

	public Card addCard(String name, String description, String family, String affinity, String imgUrl, String smallImgUrl,
			float attack, float defense, float hp, float price, int energy) {
		

	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	    // create a post object
	    Card c = new Card(name, description, family, affinity,  imgUrl, smallImgUrl,
				attack, defense, hp, price, energy);

	    // build the request
	    HttpEntity<Card> entity = new HttpEntity<>(c, headers);

	    // send POST request
	    return restTemplate.postForObject(url, entity, Card.class);
	}
	
	
	public void addCard(CardFormDTO cardForm) {
	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    
	    // build the request
	    HttpEntity<CardFormDTO> entity = new HttpEntity<>(cardForm, headers);

	    // send POST request
	    restTemplate.postForObject(url, entity, CardFormDTO.class);
	}
}

