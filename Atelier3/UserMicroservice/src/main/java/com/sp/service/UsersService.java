package com.sp.service;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Random;

import com.sp.dto.CardDTO;
import com.sp.dto.UserCardDTO;
import com.sp.entity.UserCard;
import com.sp.entity.UserCardId;
import com.sp.entity.Users;
import com.sp.model.UserCardRepository;
import com.sp.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private UserCardRepository userCardRepository;


	private final RestTemplate restTemplate;
	private String urlCards = "http://localhost:8080/cards";
	
	public UsersService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<Users> getUserList() {
		List<Users> result = 
				  StreamSupport.stream(userRepository.findAll().spliterator(), false)
				  .collect(Collectors.toList());
		return result;
	}

	public Users addUser( Users u) {
		u.setPassword(u.getPassword());
		u.setWallet(5000);
				
		userRepository.save(u);
		
		CardDTO[] result = this.restTemplate.getForObject(urlCards, CardDTO[].class);
		
		Random random = new Random();
		for(int i=0;i<5;i++)
		{
			int value = random.nextInt(result.length);
			CardDTO c = Arrays.asList(result).get(value);
			
			UserCard uc = userCardRepository.findByIdCardIdAndIdUserId(c.getId(), u.getId());
			if(uc == null)
			{
				uc = new UserCard(new UserCardId(c.getId(),u.getId()), 1, 100);
			}
			else {
				uc.setQuantity(uc.getQuantity()+1);
			}
			
			userCardRepository.save(uc);
		}
		
		return u;
	}
	
	public void deleteUser(Users u) {
		userRepository.delete(u);
	}
	
	public Users findUsersById(Integer id) {
		return userRepository.findById(id);
	}
	
	public List<Users> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public void updateUser(Users u) {
		Users user = userRepository.findById(u.getId());
		
		//Modification
		user.setWallet(u.getWallet());			
		
		//Enregistrement
		userRepository.save(user);
	}
	
	public List<UserCard> getInventory(Integer userId) {
		List<UserCard> inventaire = new ArrayList<UserCard>();
		
		Users user = userRepository.findById(userId);
		if (user == null) {
			System.out.println("Le user avec l'id " + userId + " n'existe pas");
			return inventaire;
		}
		
		//Les ids de cartes de l'utilisateur
		inventaire = StreamSupport.stream(userCardRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		return inventaire;
	}
	
	public UserCard getInventoryByCardId(Integer userId, Integer cardId) {
		UserCard userCard = userCardRepository.findByIdCardIdAndIdUserId(userId, cardId);
		return userCard;
	}

	public void deleteUserCard(UserCard userCard) {
		userCardRepository.delete(userCard);
	}
	
	public static <T> Collector<T, ?, T> toSingleton() {
	    return Collectors.collectingAndThen(
	            Collectors.toList(),
	            list -> {
	                if (list.size() != 1) {
	                    throw new IllegalStateException();
	                }
	                return list.get(0);
	            }
	    );
	}

	public void addUserCard(UserCard userCard) {
		UserCard u = userCardRepository.findByIdCardIdAndIdUserId(userCard.getId().getCardId(), userCard.getId().getUserId());
		
		if(u != null)
		{
	        throw new IllegalStateException();
		}
		
		userCardRepository.save(userCard);
	}

	public void updateUserCard(UserCard userCard) {
		UserCard u = userCardRepository.findByIdCardIdAndIdUserId(userCard.getId().getCardId(), userCard.getId().getUserId());
		
		if(u == null)
		{
	        throw new IllegalStateException();
		}
		
		u.setQuantity(userCard.getQuantity());
		u.setEnergy(userCard.getEnergy());
		
		userCardRepository.save(u);
	}
}

