package com.api.mitra_di_chaap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.payloads.ApiResponse;
import com.api.mitra_di_chaap.payloads.FeedbackDto;
import com.api.mitra_di_chaap.services.FeedbackService;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
	
	@Autowired
	FeedbackService feedService;

	
	// create a feedback
	@PostMapping("/create")
	public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedDto){
		
		FeedbackDto myfeedDto = this.feedService.createFeedback(feedDto);
		
		return new ResponseEntity<FeedbackDto>(myfeedDto,HttpStatus.OK);
	}
	
	
	
	// get all feedbacks
	@GetMapping("/all")
	public ResponseEntity<List<FeedbackDto>> getAllFeedbacks(){
		
		List<FeedbackDto> feeds = this.feedService.getAllFeedBacks();
		
		
		return new ResponseEntity<List<FeedbackDto>>(feeds,HttpStatus.OK);
	}
	
	
	// delete a feedback
	@DeleteMapping("/delete/{feedId}")
	public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable Integer feedId){
		
		
		this.feedService.deleteFeedback(feedId);
		
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("feedback has been deleted successfully.",true),HttpStatus.OK);
	}
	
	
	// get a feedback by its id
	@GetMapping("/{feedId}")
	public ResponseEntity<FeedbackDto> getFeedById(@PathVariable Integer feedId){
		
		FeedbackDto feed = this.feedService.getFeedbackById(feedId);
		
		
		
		return new ResponseEntity<FeedbackDto>(feed,HttpStatus.OK);
	}
	
}
