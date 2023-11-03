package com.api.mitra_di_chaap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mitra_di_chaap.entities.Feedback;
import com.api.mitra_di_chaap.payloads.FeedbackDto;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	
//FeedbackDto getFeedBackByUser(Integer userId);
	
//	FeedbackDto getFeedbackById(Integer feedId);
	
	List<Feedback> getFeedsByNameIgnoreCase(String name);
}
