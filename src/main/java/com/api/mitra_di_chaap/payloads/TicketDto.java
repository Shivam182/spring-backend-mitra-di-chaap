package com.api.mitra_di_chaap.payloads;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class TicketDto {

		private Integer id;
		
		private String name;
		
		private String time;
		
		private Integer tableSize;
		
		
		private Integer userId;
	
}
