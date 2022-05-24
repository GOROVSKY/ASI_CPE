  package com.sp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
  
  @Controller
  public class RequestCrt {
	  
  	@Value("${welcome.message}")
  	private String message;
  
  	private static String messageLocal="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
  
  	@RequestMapping(value = {  "/index" }, method = RequestMethod.GET)
  	public String index(Model model) {
    
  		model.addAttribute("message", message);
  		model.addAttribute("messageLocal", messageLocal);
  
  		return "index";
  	}
  	
  	
  	@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
  	public String addUser(Model model) {
 
  		return "addUser";
  	}
  	
  	@RequestMapping(value = { "/","/login" }, method = RequestMethod.GET)
  	public String login(Model model) {
 
  		return "login";
  	}
  	
  	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
  	public String menu(Model model) {
 
  		return "menu";
  	}
  	
  	@RequestMapping(value = { "/cardBuy" }, method = RequestMethod.GET)
  	public String cardBuy(Model model) {
 
  		return "cardListBUY";
  	}
  	
  	@RequestMapping(value = { "/cardSell" }, method = RequestMethod.GET)
  	public String cardSell(Model model) {
 
  		return "cardListSELL";
  	}
  	
  	@RequestMapping(value = { "/view"}, method = RequestMethod.GET)
    public String view(Model model) {
      	return "poneyView";
  	}
  	


  	
  }

