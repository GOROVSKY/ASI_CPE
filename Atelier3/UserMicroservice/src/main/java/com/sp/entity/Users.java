package com.sp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User generated by hbm2java
 */
@Entity

public class Users implements java.io.Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private String password;
	private int wallet;

	public Users() {
	}

	public Users(int id) {
		this.id = id;
	}

	public Users(String name, String surname, String password) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.wallet=5000;
	}

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "wallet")
	public int getWallet() {
		return this.wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	@Column(name = "surname")
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", surname=" + surname  + ", password="
				+ password + "]";
	}
	
	

}