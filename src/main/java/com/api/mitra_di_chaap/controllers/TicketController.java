package com.api.mitra_di_chaap.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.payloads.TicketDto;
import com.api.mitra_di_chaap.services.TicketService;

@RestController
@RequestMapping("/api/ticketing")
public class TicketController {
	
	
	@Autowired
	private TicketService tktService;
	
	
	@PostMapping("/create")
	public ResponseEntity<TicketDto> createTkt(@RequestParam TicketDto tkt){
		
		TicketDto tktt = this.tktService.createTicket(tkt);
		
		return new ResponseEntity<TicketDto>(tktt,HttpStatus.OK);
	}
	
	
	@GetMapping("/ticket/id/{id}")
	public ResponseEntity<TicketDto> getTktById(@PathVariable String id){
		
		TicketDto tkt = this.tktService.getTicketById(id);
		
		return new ResponseEntity<TicketDto>(tkt,HttpStatus.OK);
	}
	
	
	@GetMapping("/tickets/time/{time}")
	public ResponseEntity<List<TicketDto>> getTktsByTime(@PathVariable LocalTime time){
		
		List<TicketDto> tkts = this.tktService.getTicketsByTime(time);
		
		return new ResponseEntity<List<TicketDto>>(tkts,HttpStatus.OK);
	}
	
	@GetMapping("/tickets/size/{size}")
	public ResponseEntity<List<TicketDto>> getTktByTableSize(@PathVariable Integer size){
		
		List<TicketDto> tkts = this.tktService.getTicketByTableSize(size);
		
		return new ResponseEntity<List<TicketDto>>(tkts, HttpStatus.OK);
	}

	@GetMapping("/tickets/name/{name}")
	public ResponseEntity<List<TicketDto>> getTktByName(@PathVariable String name){
		
		List<TicketDto> tkts = this.tktService.getTicketsByName(name);
		
		return new ResponseEntity<List<TicketDto>>(tkts, HttpStatus.OK);
	}
	
}
