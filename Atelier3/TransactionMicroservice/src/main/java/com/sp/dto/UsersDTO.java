package com.sp.dto;

public class UsersDTO  {

	private String name;
	private String surname;
	private int wallet;
	private int id ;


	public UsersDTO() {
		this.id=0;
		this.name = "";
		this.surname = "";
		this.wallet=0;

	}
	
	public UsersDTO(String name,String surname,int wallet,int id) {
		this.name = name;
		this.surname = surname;
		this.wallet=wallet;
		this.id=id;
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

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}


