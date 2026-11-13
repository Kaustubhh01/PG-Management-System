package com.pgmanagement.controller;

import com.pgmanagement.model.Room;
import com.pgmanagement.service.BookingService;
import com.pgmanagement.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService service;
    
    @Autowired
    private BookingService bookingService;

    // Add Room API
    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {

        return service.addRoom(room);
    }

    // Get All Rooms API
    @GetMapping("/all")
    public List<Room> getAllRooms() {

        bookingService.updateExpiredBookings();

        return service.getAllRooms();
    }
    
 // Delete Room API
    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable int id) {

        service.deleteRoom(id);

        return "Room Deleted Successfully";
    }

 // Update Room API
    @PutMapping("/update/{id}")
    public Room updateRoom(
            @PathVariable int id,
            @RequestBody Room room) {

        return service.updateRoom(id, room);
    }
}