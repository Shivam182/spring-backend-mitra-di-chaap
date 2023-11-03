package com.api.mitra_di_chaap.payloads;


import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CartDto {

	
	private Integer id;
	private Integer total;
//	private int count;
	private Map<Integer,Integer> food_item;
}
