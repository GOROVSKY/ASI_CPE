package com.sp.model;

import java.util.List;



import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.

public interface CardTagRepository extends CrudRepository<List<String>, Long> {
	

}