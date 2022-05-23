package com.sp.dto;

public class UsersDTO  {

	private String name;
	private String surname;



	public UsersDTO() {
		this.name = "";
		this.surname = "";

	}
	
	public UsersDTO(String name,String surname) {
		this.name = name;
		this.surname = surname;

	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}


