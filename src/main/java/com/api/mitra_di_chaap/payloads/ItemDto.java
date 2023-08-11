package com.api.mitra_di_chaap.payloads;

import java.util.HashSet;
import java.util.Set;


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
	
	private String image1;
	
	private String image2;
	
	private String image3;
	
	private CategoryDto category;
	
	private Integer price;
	
	private Integer stock;
	
	private Double ratings;
	
	private Set<ReviewsDto> reviews = new HashSet<>();
	
}
