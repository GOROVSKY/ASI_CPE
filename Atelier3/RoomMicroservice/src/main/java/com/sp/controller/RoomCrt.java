package com.sp.controller;


import java.util.ArrayList;
import java.util.List;

import com.sp.dto.RoomDTO;
import com.sp.entity.Room;
import com.sp.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomCrt {

	@Autowired
	RoomService roomService;

	@RequestMapping(value = { "/rooms" }, method = RequestMethod.GET)
	public List<RoomDTO> getRooms() {
		List<RoomDTO> dtoList = new ArrayList<RoomDTO>();
		for (Room room : roomService.getRooms()) {
			dtoList.add(new RoomDTO(room.getId(),room.getIdUser1(),room.getIdUser2(),room.getIdCard1(),room.getIdCard2(),room.getMise() ));
		}
		return dtoList;
	}

	@RequestMapping(value = { "/rooms" }, method = RequestMethod.POST)
	public RoomDTO addRoom(@RequestBody Room room) {
		roomService.addRoom(room);
		return new RoomDTO(room.getId(),room.getIdUser1(),room.getIdUser2(),room.getIdCard1(),room.getIdCard2(),room.getMise());
	}

	@RequestMapping(value = { "/rooms" }, method = RequestMethod.PUT)
	public Room updateRoom(@RequestBody Room room) {
		return roomService.updateRoom(room);
	}

	@RequestMapping(value = { "/rooms" }, method = RequestMethod.DELETE)
	public void deleteroom(@RequestBody Room room) {
		roomService.deleteRoom(room);
	}
}