package com.pgmanagement.controller;

import com.pgmanagement.model.Booking;
import com.pgmanagement.repository.UserRepository;
import com.pgmanagement.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import com.pgmanagement.dto.BookingResponse;
import com.pgmanagement.model.User;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService service;
    
    @Autowired
    private UserRepository userRepo;

    // Add Booking API
    @PostMapping("/add")
    public ResponseEntity<?> addBooking(
            @RequestBody Booking booking){

        Booking savedBooking =
                service.addBooking(booking);

        if(savedBooking == null){

            return ResponseEntity
                    .badRequest()
                    .body("Booking Failed");
        }

        return ResponseEntity.ok(savedBooking);
    }

    // Get All Bookings API
    @GetMapping("/all")
    public List<BookingResponse> getAllBookings(){

        List<Booking> bookings =
                service.getAllBookings();

        List<BookingResponse> result =
                new ArrayList<>();

        for(Booking booking : bookings){

            User user =
                    userRepo.findById(
                            booking.getUserId())
                            .orElse(null);

            BookingResponse dto =
                    new BookingResponse();

            dto.setBookingId(
                    booking.getBookingId());

            dto.setUserName(
                    user != null
                    ? user.getName()
                    : "Unknown User");

            dto.setRoomId(
                    booking.getRoomId());

            dto.setCheckInDate(
                    booking.getCheckInDate());

            dto.setCheckOutDate(
                    booking.getCheckOutDate());

            result.add(dto);
        }

        return result;
    }
    
 // Get User Bookings API
    @GetMapping("/user/{id}")
    public List<Booking> getBookingsByUserId(
            @PathVariable int id){

        return service.getBookingsByUserId(id);
    }

    // Cancel Booking API
    @DeleteMapping("/delete/{id}")
    public String cancelBooking(
            @PathVariable int id){

        service.cancelBooking(id);

        return "Booking Cancelled Successfully";
    }
}