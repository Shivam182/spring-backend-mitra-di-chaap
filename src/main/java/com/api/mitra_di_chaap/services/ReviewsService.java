package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.ReviewsDto;

public interface ReviewsService {
	
	// create a review
	ReviewsDto createReview(ReviewsDto reviewsDto, Integer itemId, Integer userId);
	
	
	// delete a review
	void deleteReview(Integer reviewId, Integer userId);
	
	
	// get all reviews
	List<ReviewsDto> allreviews(Integer itemId);
	
	// edit a review
	
}
