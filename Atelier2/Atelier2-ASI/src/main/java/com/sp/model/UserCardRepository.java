package com.sp.model;


import java.util.List;

import com.sp.entity.UserCard;
import com.sp.entity.UserCardId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.
public interface UserCardRepository extends CrudRepository<UserCard, UserCardId> { // Long: Type of Card ID.

	UserCard findById(int UserCardId);
	
	UserCard findByIdCardIdAndIdUserId(int UserId,int CardId);

	
	
	List<UserCard> findByIdUserId(int userId);

	
}