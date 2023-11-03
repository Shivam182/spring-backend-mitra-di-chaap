package com.api.mitra_di_chaap.repositories;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mitra_di_chaap.entities.Ticketing;

public interface TicketRepo extends JpaRepository<Ticketing, Integer> {
	
	List<Ticketing> findTktByName(String name);
	
	List<Ticketing> findTktByTime(LocalTime time);
	
	List<Ticketing> findTktByTableSize(Integer tableSize);
	
	List<Ticketing> findByUserId(Integer userId);
	
	
}
