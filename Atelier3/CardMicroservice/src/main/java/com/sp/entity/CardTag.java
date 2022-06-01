package com.sp.entity;
// Generated 12 mai 2022, 16:44:08 by Hibernate Tools 4.3.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

/**
 * UserCard generated by hbm2java
 */
@Entity
@Table(name = "Tag_card", schema = "public")
public class CardTag implements java.io.Serializable {

	private CardTagId id;
	private List<String> tag;

	public CardTag() {
	}

	public CardTag(CardTagId id) {
		this.id = id;
	}

	public CardTag(CardTagId id, List<String> tag) {
		this.id = id;
		this.tag = tag;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "tagId", column = @Column(name = "tag_Id", nullable = false)),
			@AttributeOverride(name = "cardId", column = @Column(name = "card_Id", nullable = false)) })
	public CardTagId getId() {
		return this.id;
	}

	public void setId(CardTagId id) {
		this.id = id;
	}

	@Column(name = "tag")
	public List<String> getListtag() {
		return this.tag;
	}

	public void setListtag(List<String> tag) {
		this.tag = tag;
	}

}
