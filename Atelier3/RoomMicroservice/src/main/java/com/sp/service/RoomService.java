package com.sp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sp.entity.Room;
import com.sp.model.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
		
	@Autowired
	RoomRepository roomRepository ;

	public void addRoom(Room room) {
		roomRepository.save(room);
	}
	
	public List<Room> getRooms() {
		return roomRepository.findByIdGagnantIs(0);
//		List<Card> result = 
//				  StreamSupport.stream(cardRepository.findAll().spliterator(), false)
//				    .collect(Collectors.toList());
	}

	public Room updateRoom(Room room) {
		Room roomDb = roomRepository.findById(room.getId());
		if(room.getIdUser2()!=0) {
			roomDb.setIdUser2(room.getIdUser2());
		}
		if(room.getIdCard2()!=0) {
			roomDb.setIdCard2(room.getIdCard2());
		}
		if(room.getIdGagnant()!=0) {
			roomDb.setIdGagnant(room.getIdGagnant());
		}
		roomRepository.save(roomDb);
		return roomDb;
	}
	
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}
}
