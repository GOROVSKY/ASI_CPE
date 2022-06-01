package com.sp.authentification.service;

import java.util.ArrayList;

import com.sp.authentification.model.CardUserDetails;
import com.sp.dto.UsersDTO;
import com.sp.service.HttpRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private HttpRequestService httpRequestService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsersDTO user = httpRequestService.getUsersByName(username);
		if(user!=null) {
			
			CardUserDetails usd = new CardUserDetails(user.getName(), user.getPassword(),new ArrayList<>(),
					user.getId());
			return usd;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}