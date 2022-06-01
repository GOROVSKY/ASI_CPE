package com.sp.authentification.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CardUserDetails extends User {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private int id;
	
	public CardUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities,int id) {
		super(username, password, true, true, true, true, authorities);
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}