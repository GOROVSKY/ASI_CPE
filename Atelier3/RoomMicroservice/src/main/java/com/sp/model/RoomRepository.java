package com.sp.model;

import java.util.List;

import com.sp.entity.Room;

import org.springframework.data.repository.CrudRepository;

// This is an Interface.
// No need Annotation here.
public interface RoomRepository extends CrudRepository<Room, Long> { // Long: Type of Card ID.

	Room findById(int id);
	List<Room> findByIdGagnantIs(int alwaysZeroId);
	List<Room> findByOrderByIdAsc();

}