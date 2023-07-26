package com.api.mitra_di_chaap.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.api.mitra_di_chaap.entities.Order;

public interface OrderRepo extends JpaRepositoryImplementation<Order, Integer> {
	
}
