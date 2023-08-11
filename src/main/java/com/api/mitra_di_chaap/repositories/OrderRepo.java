package com.api.mitra_di_chaap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.api.mitra_di_chaap.entities.Order;

public interface OrderRepo extends JpaRepositoryImplementation<Order, Integer> {
	
	List<Order> findByAddressIgnoreCase(String addr);
	
	List<Order> findByStatusIgnoreCase(String status);
	
	List<Order> findByPriceBetween(Integer v1,Integer v2);
	
}
