package com.api.mitra_di_chaap.services;

import java.time.LocalTime;
import java.util.List;

import com.api.mitra_di_chaap.payloads.TicketDto;

public interface TicketService {
	
	TicketDto createTicket(TicketDto ticket);
	
	TicketDto getTicketById(String id);
	
	List<TicketDto> getTicketsByName(String name);
	
	List<TicketDto> getTicketsByTime(LocalTime time);
	
	List<TicketDto> getTicketByTableSize(Integer tableSize);
	
	void deleteTicket(String id);
	

}
