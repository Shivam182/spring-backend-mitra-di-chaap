package com.api.mitra_di_chaap.payloads;

import com.api.mitra_di_chaap.entities.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsDto {
	
	private Integer id;
	
	private String content;
	
//	private Integer userId;
	
	private String userName;
	
	
	// though it saves to the database easily but was showing error in getting response due to this being present in the DTO?
	// though its presence anywhere else does not affects and everything goes well.
//	private Item foodItem;
	
	
}
