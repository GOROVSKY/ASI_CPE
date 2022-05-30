package com.sp.dto;

import java.math.BigInteger;

public class CardDTO {

	private int id;
	private String name;
	private String description;
	private String family;
	private String affinity;
	private BigInteger attack;
	private BigInteger defense;
	private BigInteger hp;
	private BigInteger energy;
	private BigInteger price;
	private String imageUrl;

	public CardDTO(int id, String name, String description, String family, String affinity, BigInteger attack,
			BigInteger defense, BigInteger hp, BigInteger energy, BigInteger price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.family = family;
		this.affinity = affinity;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.energy = energy;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public BigInteger getAttack() {
		return attack;
	}

	public void setAttack(BigInteger attack) {
		this.attack = attack;
	}

	public BigInteger getDefense() {
		return defense;
	}

	public void setDefense(BigInteger defense) {
		this.defense = defense;
	}

	public BigInteger getHp() {
		return hp;
	}

	public void setHp(BigInteger hp) {
		this.hp = hp;
	}

	public BigInteger getEnergy() {
		return energy;
	}

	public void setEnergy(BigInteger energy) {
		this.energy = energy;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
