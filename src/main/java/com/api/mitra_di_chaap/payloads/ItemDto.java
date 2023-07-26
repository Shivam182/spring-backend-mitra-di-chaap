package com.api.mitra_di_chaap.payloads;

import java.util.HashSet;
import java.util.Set;

import com.api.mitra_di_chaap.entities.Cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
	
	private Integer itemId;
	
	private String title;
	
	private String description;
	
	private String imageName;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Cart cart;
	
	private Integer price;
	
	private Set<ReviewsDto> reviews = new HashSet<>();
	
}
