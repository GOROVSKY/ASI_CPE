package com.sp.service;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Random;

import com.sp.dto.CardDTO;
import com.sp.entity.UserCard;
import com.sp.entity.UserCardId;
import com.sp.entity.Users;
import com.sp.model.UserCardRepository;
import com.sp.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private UserCardRepository userCardRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;;


	private final RestTemplate restTemplate;
	private String urlCards = "https://localhost/cards";
	
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
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		u.setWallet(5000);
		
		CardDTO[] result = this.restTemplate.getForObject(urlCards, CardDTO[].class);
		
		userRepository.save(u);
		
		Random random = new Random();
		for(int i=0;i<5;i++)
		{
			int value = random.nextInt(result.length);
			CardDTO c = Arrays.asList(result).get(value);
			
			UserCard uc = userCardRepository.findByIdCardIdAndIdUserId(c.getId(), u.getId());
			if(uc == null)
			{
				uc = new UserCard(new UserCardId(c.getId(),u.getId()),1);
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
	
//	public List<Users> findUsersIdByJwtToken(String jwtToken) {
//		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//		return userRepository.findByName(username);
//	}
}

