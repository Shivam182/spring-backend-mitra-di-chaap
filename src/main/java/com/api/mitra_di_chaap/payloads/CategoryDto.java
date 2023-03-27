package com.api.mitra_di_chaap.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	private int categoryId;
	
	@NotBlank
	@Size(min=4)
	private String categoryTitle;
	
	
	@NotBlank
	@Size(max=100)
	private String categoryDescription;
	
}
