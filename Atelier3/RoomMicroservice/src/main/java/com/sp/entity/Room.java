package com.sp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room implements java.io.Serializable {
	@Id
	@GeneratedValue
	private int id;
	private int idUser1;
	private int idUser2;
	private int idCard1;
	private int idCard2;
	private int mise;
	private int idGagnant;

	public Room() {
	}

	public Room(int id) {
		this.id = id;
	}
	
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser1() {
		return idUser1;
	}

	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}

	public int getIdUser2() {
		return idUser2;
	}

	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}

	public int getIdCard1() {
		return idCard1;
	}

	public void setIdCard1(int idCard1) {
		this.idCard1 = idCard1;
	}

	public int getIdCard2() {
		return idCard2;
	}

	public void setIdCard2(int idCard2) {
		this.idCard2 = idCard2;
	}

	public int getMise() {
		return mise;
	}

	public void setMise(int mise) {
		this.mise = mise;
	}
	
	public int getIdGagnant() {
		return idGagnant;
	}

	public void setIdGagnant(int idGagnant) {
		this.idGagnant = idGagnant;
	}

	public Room(int id, int idUser1, int idUser2, int idCard1, int idCard2, int mise) {
		super();
		this.id = id;
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		this.idCard1 = idCard1;
		this.idCard2 = idCard2;
		this.mise = mise;
	}
	
}
