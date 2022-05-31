package com.sp.dto;

import java.io.Serializable;

public class PasswordHashDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String password;
	
	//need default constructor for JSON Parsing
	public PasswordHashDTO()
	{
		
	}

	public PasswordHashDTO( String password) {
		this.setPassword(password);
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}