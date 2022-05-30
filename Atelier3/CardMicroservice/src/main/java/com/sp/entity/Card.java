package com.sp.entity;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Card implements java.io.Serializable {
	@Id
	@GeneratedValue
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

	public Card() {
	}

	public Card(int id) {
		this.id = id;
	}

	public Card(int id, String name, String description, String family, String affinity, BigInteger attack,
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

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "family")
	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Column(name = "affinity")
	public String getAffinity() {
		return this.affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	@Column(name = "attack", precision = 131089, scale = 0)
	public BigInteger getAttack() {
		return this.attack;
	}

	public void setAttack(BigInteger attack) {
		this.attack = attack;
	}

	@Column(name = "defense", precision = 131089, scale = 0)
	public BigInteger getDefense() {
		return this.defense;
	}

	public void setDefense(BigInteger defense) {
		this.defense = defense;
	}

	@Column(name = "hp", precision = 131089, scale = 0)
	public BigInteger getHp() {
		return this.hp;
	}

	public void setHp(BigInteger hp) {
		this.hp = hp;
	}

	@Column(name = "energy", precision = 131089, scale = 0)
	public BigInteger getEnergy() {
		return this.energy;
	}

	public void setEnergy(BigInteger energy) {
		this.energy = energy;
	}

	@Column(name = "price", precision = 131089, scale = 0)
	public BigInteger getPrice() {
		return this.price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	@Column(name = "imageUrl")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
