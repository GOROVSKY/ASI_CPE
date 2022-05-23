package com.sp.model;

import java.util.List;

import com.sp.entity.Users;

import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.
public interface UserRepository extends CrudRepository<Users, Long> { // Long: Type of Employee ID.

	Users findById(int id);

    List<Users> findByNameLike(String fullName);
    List<Users> findBySurnameLike(String surname);

}