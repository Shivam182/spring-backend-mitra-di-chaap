package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Feedback;
import com.api.mitra_di_chaap.entities.User;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.FeedbackDto;
import com.api.mitra_di_chaap.repositories.FeedbackRepo;
import com.api.mitra_di_chaap.repositories.UserRepo;
import com.api.mitra_di_chaap.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	private UserRepo userRepo;

	
	
	@Override
	public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
		Feedback feedback =  this.modelMapper.map(feedbackDto, Feedback.class);
		
		Feedback newFeed = this.feedbackRepo.save(feedback);
		
		
		
		return this.modelMapper.map(newFeed, FeedbackDto.class);
	}
	
	

	@Override
	public void deleteFeedback(Integer feedId) {
		Feedback feed = this.feedbackRepo.findById(feedId).orElseThrow(()->new ResourceNotFoundException("Feedback","feedback id",feedId));
		
		this.feedbackRepo.delete(feed);
	}

	@Override
	public List<FeedbackDto> getAllFeedBacks() {
		
		List<Feedback> feeds = this.feedbackRepo.findAll();
		
		List<FeedbackDto> feedDtos = feeds.stream().map((feed)->this.modelMapper.map(feed, FeedbackDto.class)).collect(Collectors.toList());
		
		
		
		return feedDtos;
	}

//	@Override
//	public FeedbackDto getFeedBackByUser(Integer userId) {
//		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId));
//		Feedback feed =  this.feedbackRepo.findBy
//		return null;
//	}

	@Override
	public FeedbackDto getFeedbackById(Integer feedId) {
		Feedback feed = this.feedbackRepo.findById(feedId).orElseThrow(()-> new ResourceNotFoundException("Feedback","feedback id",feedId));
		FeedbackDto feedbackDto = this.modelMapper.map(feed, FeedbackDto.class);
		
		return feedbackDto;
	}

}
