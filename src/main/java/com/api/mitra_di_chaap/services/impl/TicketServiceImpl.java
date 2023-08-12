package com.api.mitra_di_chaap.services.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Ticketing;
import com.api.mitra_di_chaap.payloads.TicketDto;
import com.api.mitra_di_chaap.repositories.TicketRepo;
import com.api.mitra_di_chaap.services.TicketService;


@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TicketRepo tktRepo;

	
	
	@Override
	public TicketDto getTicketById(String id) {
		@SuppressWarnings("deprecation")
		Ticketing tkt = this.tktRepo.getById(id);
		
		TicketDto tktDto = this.modelMapper.map(tkt, TicketDto.class);
		
		return tktDto;
	}
	

	@Override
	public List<TicketDto> getTicketsByName(String name) {
		List<Ticketing> tkts = this.tktRepo.findTktByName(name);
		
		List<TicketDto> tktDtos = tkts.stream().map((item)->this.modelMapper.map(item, TicketDto.class)).collect(Collectors.toList());
		
		
		return tktDtos;
	}

	@Override
	public List<TicketDto> getTicketsByTime(LocalTime time) {
List<Ticketing> tkts = this.tktRepo.findTktByTime(time);
		
		List<TicketDto> tktDtos = tkts.stream().map((item)->this.modelMapper.map(item, TicketDto.class)).collect(Collectors.toList());
		
		return tktDtos;
	}

	@Override
	public List<TicketDto> getTicketByTableSize(Integer tableSize) {

List<Ticketing> tkts = this.tktRepo.findTktByTableSize(tableSize);
		
		List<TicketDto> tktDtos = tkts.stream().map((item)->this.modelMapper.map(item, TicketDto.class)).collect(Collectors.toList());
		
		
		return tktDtos;
	}

	@Override
	public TicketDto createTicket(TicketDto ticket) {

		
		Ticketing tkt = this.modelMapper.map(ticket, Ticketing.class);
		this.tktRepo.save(tkt);
		
		return this.modelMapper.map(tkt, TicketDto.class);
	}

	@Override
	public void deleteTicket(String id) {
		
		Ticketing tkt = this.tktRepo.getById(id);
		
		
		this.tktRepo.delete(tkt);
		
		
		
		
	}

}
