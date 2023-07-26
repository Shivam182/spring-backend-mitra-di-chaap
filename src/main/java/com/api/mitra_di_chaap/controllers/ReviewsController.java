package com.api.mitra_di_chaap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.payloads.ApiResponse;
import com.api.mitra_di_chaap.payloads.ReviewsDto;
import com.api.mitra_di_chaap.services.ReviewsService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {
	@Autowired
	private ReviewsService reviewsService;
	
	
//	@PreAuthorize("#userId == authentication.principal.id")
	@PostMapping("/user/{userId}/item/{itemId}")
	public ResponseEntity<ReviewsDto> createReview(@RequestBody ReviewsDto reviews,@PathVariable Integer itemId,@PathVariable Integer userId){
		
		
		ReviewsDto reviewsDto = this.reviewsService.createReview(reviews, itemId,userId);
		
		return new ResponseEntity<ReviewsDto>(reviewsDto,HttpStatus.CREATED);
		
	}
	
	
	// only accessible to writer
//	@PreAuthorize("#userId == authentication.principal.id")
	@DeleteMapping("/user/{userId}/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer reviewId,@PathVariable Integer userId){
		
		this.reviewsService.deleteReview(reviewId,userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted comment successfully",true),HttpStatus.CREATED);
		
	}
	
	
	//  accessible to everyone 
	@GetMapping("/item/{itemId}")
	public ResponseEntity<List<ReviewsDto>> getAllReviews(@PathVariable Integer itemId){
		
		List<ReviewsDto> allreviews = this.reviewsService.allreviews(itemId);
		
		
		return new ResponseEntity<List<ReviewsDto>>(allreviews,HttpStatus.OK);
		
	}
	
	
}
