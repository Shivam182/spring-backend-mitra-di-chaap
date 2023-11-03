package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.FeedbackDto;

public interface FeedbackService {

	FeedbackDto createFeedback(FeedbackDto feedbackDto);
	
	void deleteFeedback(Integer feedId);
	
	List<FeedbackDto> getAllFeedBacks();
	
	List<FeedbackDto> getFeedBackByName(String name);
	
	FeedbackDto getFeedbackById(Integer feedId);
	
}
