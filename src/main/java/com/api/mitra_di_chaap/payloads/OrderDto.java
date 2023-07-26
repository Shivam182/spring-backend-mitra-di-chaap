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

	private int orderId;
	private String status;
	private int price;
	private String address;
	
	
	private Map<Integer, Integer> items = new HashMap<>();
	
}
