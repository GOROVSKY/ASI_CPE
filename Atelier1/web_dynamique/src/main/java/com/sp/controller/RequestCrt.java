package com.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.model.Card;
import com.sp.model.CardFormDTO;



@Controller // AND NOT @RestController
public class RequestCrt {
	@Autowired
	CardDao cardDao;


	@Value("${welcome.message}")
	private String message;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/view"}, method = RequestMethod.GET)
	public String view(Model model) {
		model.addAttribute("myCard",cardDao.getRandomCard() );
		return "cardView";
	}

	@RequestMapping(value = { "/addCard"}, method = RequestMethod.GET)
	public String addcard(Model model) {
		CardFormDTO cardForm = new CardFormDTO();
		model.addAttribute("cardForm", cardForm);
		return "cardForm";
	}

	@RequestMapping(value = { "/addCard"}, method = RequestMethod.POST)
	public String addcard(Model model, @ModelAttribute("cardForm") CardFormDTO cardForm) {
		System.out.println("toto");
//		Card card = cardDao.addCard(cardForm);
//		model.addAttribute("myCard", card);
		cardDao.addCard(cardForm);
		return "cardView"; 
	}

	@RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public String viewList(Model model) {
  	  model.addAttribute("cardList", cardDao.getCardList() );
  	  return "cardViewList";
    }




}