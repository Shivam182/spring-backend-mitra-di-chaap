package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.entities.Reviews;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.ReviewsDto;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.repositories.ReviewsRepo;
import com.api.mitra_di_chaap.services.ReviewsService;


@Service
public class ReviewsServiceImpl implements ReviewsService {

	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("#userMail == authentication.principal.email")
	@Override
	public ReviewsDto createReview(ReviewsDto reviewsDto, Integer itemId,String userMail) {
		
		// get the food item
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		
		
		Reviews review = this.modelMapper.map(reviewsDto, Reviews.class);
		
		review.setFoodItem(item);
//		review.setUserId(userId);
		review.setUserName(userMail);
		
		
		Reviews savedReview = this.reviewsRepo.save(review);
		
		return this.modelMapper.map(savedReview, ReviewsDto.class);
	}
	
	@PreAuthorize("#userId == authentication.principal.id")
	@Override
	public void deleteReview(Integer reviewId, Integer userId) {
		
		
		// TODO only owner of the review can delete it 
		
		Reviews review =  this.reviewsRepo.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("Review","review id",reviewId));
		
		this.reviewsRepo.delete(review);
	}


	@Override
	public List<ReviewsDto> allreviews(Integer itemId) {
		
		// get an item
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
	
		// get all its reviews
		Set<Reviews> reviews = item.getReviews();
		
		List<ReviewsDto> reviewsDtos = reviews.stream().map((review)-> this.modelMapper.map(review, ReviewsDto.class)).collect(Collectors.toList());
		
		return reviewsDtos;
		
	}

}
