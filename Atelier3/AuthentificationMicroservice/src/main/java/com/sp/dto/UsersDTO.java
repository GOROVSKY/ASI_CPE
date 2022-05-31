package com.sp.dto;

public class UsersDTO {

	private String name;
	private String surname;
	private String password;
	private int wallet;
	private int id;

	public UsersDTO() {
		this.id = 0;
		this.name = "";
		password = "";
		this.surname = "";
		this.wallet = 0;

	}

	public UsersDTO(String name, String surname, String password, int wallet, int id) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.wallet = wallet;
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsersDTO [name=" + name + ", surname=" + surname + ", password=" + password + ", wallet=" + wallet
				+ ", id=" + id + "]";
	}
	
	

}
