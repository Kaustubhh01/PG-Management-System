package com.pgmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmanagement.repository.BookingRepository;
import com.pgmanagement.repository.RoomRepository;
import com.pgmanagement.repository.UserRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminStatsController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @GetMapping("/stats")
    public Map<String,Object> getStats(){

        Map<String,Object> stats =
                new HashMap<>();

        stats.put(
                "totalUsers",
                userRepo.count());

        stats.put(
                "totalRooms",
                roomRepo.count());

//        stats.put(
//                "availableRooms",
//                roomRepo.countByAvailability(
//                        "Available"));

//        stats.put(
//                "bookedRooms",
//                roomRepo.countByAvailability(
//                        "Booked"));
//
//        stats.put(
//                "bookedRooms",
//                bookingRepo.count());
        
        stats.put(
                "totalBookings",
                bookingRepo.count());

        return stats;
    }
}
