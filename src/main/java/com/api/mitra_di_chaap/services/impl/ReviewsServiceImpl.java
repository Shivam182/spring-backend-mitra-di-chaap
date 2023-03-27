package com.api.mitra_di_chaap.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.entities.Reviews;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.ReviewsDto;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.repositories.ReviewsRepo;
import com.api.mitra_di_chaap.services.ReviewsService;

public class ReviewsServiceImpl implements ReviewsService {

	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ReviewsDto createReview(ReviewsDto reviewsDto, Integer itemId) {
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		Reviews review = this.modelMapper.map(reviewsDto, Reviews.class);
		
		review.setFood_item(item);
		
		Reviews savedReview = this.reviewsRepo.save(review);
		
		return this.modelMapper.map(savedReview, ReviewsDto.class);
	}
	

	@Override
	public void deleteReview(Integer reviewId) {
		
		Reviews review =  this.reviewsRepo.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("Review","review id",reviewId));
		
		this.reviewsRepo.delete(review);
	}

}
