package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.CategoryDto;

public interface CategoryService {
	
	
	// create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// update 
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	// delete
	void deleteCategory(Integer categoryId);
	
	// get 
	CategoryDto getCategory(Integer categoryId);
	
	// getAll
	List<CategoryDto> getCategories();
	
}
