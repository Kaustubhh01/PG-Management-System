package com.pgmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanagement.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	long countByAvailability(String availability);
}
