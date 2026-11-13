package com.pgmanagement.service;

import com.pgmanagement.model.Room;
import com.pgmanagement.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    // Add Room
    public Room addRoom(Room room) {

        return repo.save(room);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {

        return repo.findAll();
    }
    
 // Delete Room
    public void deleteRoom(int id) {

        repo.deleteById(id);
    }

 // Update Room
    public Room updateRoom(int id, Room updatedRoom) {

        Room room = repo.findById(id).orElse(null);

        if(room != null){

            room.setRoomType(updatedRoom.getRoomType());

            room.setPrice(updatedRoom.getPrice());

            room.setAvailability(
                updatedRoom.getAvailability()
            );

            room.setImageUrl(
                updatedRoom.getImageUrl()
            );

            return repo.save(room);
        }

        return null;
    }
}