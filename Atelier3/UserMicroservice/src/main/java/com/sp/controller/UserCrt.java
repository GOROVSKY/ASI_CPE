package com.sp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.sp.dto.CardDTO;
import com.sp.dto.UserCardDTO;
import com.sp.dto.UsersDTO;
import com.sp.entity.UserCard;
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

	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public List<UsersDTO> users() {
		List<UsersDTO> dtoList = new ArrayList<UsersDTO>();
		for (Users users : usersService.getUserList()) {
			dtoList.add(new UsersDTO(users.getName(), users.getSurname(),users.getWallet(),users.getId()));
		}
		return dtoList;

	}

	@RequestMapping(value = { "/users" }, method = RequestMethod.POST)
	public String addUser(@RequestBody Users user) {
		usersService.addUser(user);
		return user.toString();
	}

	@RequestMapping(value = { "/users/{userId}" }, method = RequestMethod.GET)
	public Users getUsersById(@PathVariable @NotNull @DecimalMin("0") Integer userId) {
		Users u = usersService.findUsersById(userId);
		return u;
	}

	@RequestMapping(value = { "/users" }, method = RequestMethod.DELETE)
	public Users deleteUser(@RequestBody Users user) {
		usersService.deleteUser(user);
		return user;
	}
	
	@RequestMapping(value = { "/users" }, method = RequestMethod.PUT)
	public Users updateUser(@RequestBody Users user) {
		usersService.updateUser(user);
		return user;
	}
	
	@RequestMapping(value = { "/users/{userId}/inventory" }, method = RequestMethod.GET)
	public List<UserCardDTO> getUsersInventoryById(@PathVariable @NotNull @DecimalMin("0") Integer userId) {
		List<UserCard> liste = usersService.getInventory(userId);
		
		List<UserCardDTO> listeDTO = new ArrayList<UserCardDTO>();
		
		for (UserCard userCard : liste) {
			listeDTO.add(new UserCardDTO(userCard.getId().getCardId(), userCard.getId().getUserId() ,userCard.getQuantity(), 100));
		}
		
		return listeDTO;
	}
	
	@RequestMapping(value = { "/users/{userId}/inventory/{cardId}" }, method = RequestMethod.GET)
	public UserCardDTO getUsersInventoryById(@PathVariable @NotNull @DecimalMin("0") Integer userId, @PathVariable @NotNull @DecimalMin("0") Integer cardId) {
		UserCard userCard = usersService.getInventoryByCardId(userId, cardId);
		
		UserCardDTO dto = new UserCardDTO(cardId, userId, userCard.getQuantity(), 100);
		return dto;
	}
	
	
	@RequestMapping(value = { "/users/{userId}/inventory" }, method = RequestMethod.DELETE)
	public void deleteUserCard(@RequestBody UserCard userCard) {
		usersService.deleteUserCard(userCard);
	}
	
	@RequestMapping(value = { "/users/{userId}/inventory" }, method = RequestMethod.POST)
	public UserCard addUserCard(@RequestBody UserCard userCard) {
		usersService.addUserCard(userCard);
		return userCard;
	}
	
	@RequestMapping(value = { "/users/{userId}/inventory" }, method = RequestMethod.PUT)
	public void updateUserCard(@RequestBody UserCard userCard) {
		usersService.updateUserCard(userCard);
	}
}
