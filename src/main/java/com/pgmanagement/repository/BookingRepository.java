package com.pgmanagement.repository;

import com.pgmanagement.model.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, Integer> {

	List<Booking> findByUserId(int userId);

    List<Booking> findByRoomId(int roomId);
}