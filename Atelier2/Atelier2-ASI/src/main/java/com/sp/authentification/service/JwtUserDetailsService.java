package com.sp.authentification.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sp.entity.Users;
import com.sp.service.UsersService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    UsersService usersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername ");
		if (usersService.findByName(username).size()>0) {
			System.out.println("userrs");
			Users u = usersService.findByName(username).get(0);
			return new User(u.getName(), u.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}