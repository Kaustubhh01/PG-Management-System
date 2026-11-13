package com.pgmanagement.service;

import com.pgmanagement.model.Booking;
import com.pgmanagement.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;


    // Add Booking
    public Booking addBooking(Booking booking){

    	System.out.println("================================");
    	System.out.println("Room ID: " + booking.getRoomId());
    	System.out.println("CheckIn: " + booking.getCheckInDate());
    	System.out.println("CheckOut: " + booking.getCheckOutDate());
    	System.out.println("================================");
    	
        List<Booking> existingBookings =
                bookingRepo.findByRoomId(
                        booking.getRoomId());

        LocalDate newCheckIn =
                LocalDate.parse(
                        booking.getCheckInDate());

        LocalDate newCheckOut =
                LocalDate.parse(
                        booking.getCheckOutDate());

        // Prevent past booking
        if(newCheckIn.isBefore(LocalDate.now())){
        	System.out.println("Failed : Past Date");
            return null;
        }

        // Check-Out must be after Check-In
        if(newCheckOut.isBefore(newCheckIn)){
            return null;
        }

        for(Booking existing : existingBookings){

            LocalDate existingCheckIn =
                    LocalDate.parse(
                            existing.getCheckInDate());

            LocalDate existingCheckOut =
                    LocalDate.parse(
                            existing.getCheckOutDate());

            boolean overlap =
                    newCheckIn.isBefore(existingCheckOut)
                    &&
                    newCheckOut.isAfter(existingCheckIn);

            if(overlap){

                return null;
            }
        }

        return bookingRepo.save(booking);
    }

    // Get All Bookings
    public List<Booking> getAllBookings(){

        return bookingRepo.findAll();
    }
    
 // Get User Bookings
    public List<Booking> getBookingsByUserId(int userId){

        return bookingRepo.findByUserId(userId);
    }

 // Cancel Booking
    public void cancelBooking(int bookingId){

        bookingRepo.deleteById(bookingId);
    }
    
    public void updateExpiredBookings() {

        List<Booking> bookings =
                bookingRepo.findAll();

        LocalDate today =
                LocalDate.now();

        for (Booking booking : bookings) {

            LocalDate checkOutDate =
                    LocalDate.parse(
                            booking.getCheckOutDate());

            if (checkOutDate.isBefore(today)) {

                bookingRepo.deleteById(
                        booking.getBookingId());
            }
        }
    }
}