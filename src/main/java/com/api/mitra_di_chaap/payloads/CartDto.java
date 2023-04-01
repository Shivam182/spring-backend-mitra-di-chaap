package com.api.mitra_di_chaap.payloads;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CartDto {

	
	private int id;
	private int total;
	private List<Integer> food_item;
}
