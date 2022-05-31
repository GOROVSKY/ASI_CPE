package com.sp.dto;

import java.math.BigInteger;

public class UserCardDTO {

	private int cardId;
	private int userId;
	private int quantity;
	private int energy;

	public UserCardDTO(int cardId, int userId, int quantity, int energy) {
		this.cardId = cardId;
		this.userId = userId;
		this.quantity = quantity;
		this.energy = energy;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
