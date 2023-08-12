package com.api.mitra_di_chaap.entities;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="tickets")
@Getter
@Setter
public class Ticketing {
	
	@Id
	private String id;
	
	
	private String name;
	
	private Integer tableSize;
	
	private LocalTime time;
}
