package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Category;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.CategoryDto;
import com.api.mitra_di_chaap.repositories.CategoryRepo;
import com.api.mitra_di_chaap.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat  = this.modelMapper.map(categoryDto, Category.class);
		Category added_category = this.categoryRepo.save(cat);
		
		
		return this.modelMapper.map(added_category, CategoryDto.class);
	}

	
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		cat.setTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updated_cat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updated_cat, CategoryDto.class);
	}

	
	
	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category_id",categoryId));
		
		
		this.categoryRepo.delete(cat);
		
		

	}

	
	@Override
	public CategoryDto getCategory(Integer ctaegoryId) {
		Category cat = this.categoryRepo.findById(ctaegoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",ctaegoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}
	
	

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		
		
		return categoryDtos;
	}



	@Override
	public CategoryDto getCatByName(String name) {
		
		Category cat = this.categoryRepo.findCategoryByTitle(name);
		
		CategoryDto catDto = this.modelMapper.map(cat, CategoryDto.class);
		
		
		return catDto;
	}

}
