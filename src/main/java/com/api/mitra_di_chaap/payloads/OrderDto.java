package com.api.mitra_di_chaap.payloads;


import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

	private Integer orderId;
	private String status;
	private Integer price;
	private String address;
	private String email;
	
	private String orderedOn;
	
	
	private Map<Integer, Integer> items = new HashMap<>();
	
}
