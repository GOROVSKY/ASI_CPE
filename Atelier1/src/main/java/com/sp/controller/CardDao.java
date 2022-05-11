package com.sp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sp.model.Card;

@Service
public class CardDao {
	private List<Card> myCardList;
	private Random randomGenerator;

	public CardDao() {
		myCardList=new ArrayList<>();
		randomGenerator = new Random();
		createCardList();
	}

	private void createCardList() {
		Card c1 = new Card("Card 1", "test", "family1", "affinity1", "http://medias.3dvf.com/news/sitegrab/gits2045.jpg", "http://medias.3dvf.com/news/sitegrab/gits2045.jpg",
				10, 20, 30, 40, 100);

		myCardList.add(c1);
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
		
		Card c = new Card(name, description, family, affinity,  imgUrl, smallImgUrl,
				attack, defense, hp, price, energy);
		this.myCardList.add(c);
		return c;
	}
}

