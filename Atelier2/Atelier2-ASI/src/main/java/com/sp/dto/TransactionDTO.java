package com.sp.dto;


public class TransactionDTO  {

	private Integer id;
	private Integer cardId;
	private Integer sellerId;
	private Integer buyerId;
	
	public TransactionDTO(){
		this.id = 0;
		this.cardId = 0;
		this.sellerId = 0;
		this.buyerId = 0;
	}
	
	public TransactionDTO(Integer id, Integer cardId,Integer sellerId,Integer buyerId) {
		this.id = id;
		this.cardId = cardId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
	
	public Integer getCardId() {
		return cardId;
	}
	
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
