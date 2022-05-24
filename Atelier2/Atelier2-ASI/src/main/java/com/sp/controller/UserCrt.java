  package com.sp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.sp.dto.UsersDTO;
import com.sp.entity.Users;
import com.sp.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
  
  @RestController
  public class UserCrt {
	  
	@Autowired
    UsersService usersService;

  	@RequestMapping(value = { "/users"}, method = RequestMethod.GET)
    public List<UsersDTO> users() {
  		List<UsersDTO> dtoList = new ArrayList<UsersDTO>();
  		for(Users users :usersService.getUserList() ) {
  			dtoList.add(new UsersDTO(users.getName(),users.getSurname()));
  		}
  		return dtoList;
    	 
    }

  @RequestMapping(value = { "/users"}, method = RequestMethod.POST)
    public String addUser(@RequestBody Users user ) {
	  	
	    usersService.addUser(user);
	  	return user.toString();
  	}
  
  @RequestMapping(value = { "/users/{userId}"}, method = RequestMethod.GET)
  public Users getUsersById(@PathVariable @NotNull @DecimalMin("0") Integer userId ) {
	    Users u = usersService.findUsersById(userId);
	  	return u;
	}
  
  @RequestMapping(value = { "/users/"}, method = RequestMethod.DELETE)
  public Users deleteUser(@RequestBody Users user ) {
	    usersService.deleteUser(user);
	  	return user;
	}

  	
  }

