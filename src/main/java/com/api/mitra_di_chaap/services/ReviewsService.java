package com.api.mitra_di_chaap.services;

import com.api.mitra_di_chaap.payloads.ReviewsDto;

public interface ReviewsService {
	
	// create a review
	ReviewsDto createReview(ReviewsDto reviewsDto, Integer itemId);
	
	
	// delete a review
	void deleteReview(Integer reviewId);
}
