package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.FeedbackDto;

public interface FeedbackService {

	FeedbackDto createFeedback(FeedbackDto feedbackDto);
	
	void deleteFeedback(Integer feedId);
	
	List<FeedbackDto> getAllFeedBacks();
	
//	FeedbackDto getFeedBackByUser(Integer userId);
	
	FeedbackDto getFeedbackById(Integer feedId);
	
}
