package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Order;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.OrderDto;
import com.api.mitra_di_chaap.repositories.OrderRepo;
import com.api.mitra_di_chaap.services.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired 
	private OrderRepo orderRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	// get an order
	@Override
	public OrderDto getOrder(Integer orderId) {
		Order order =  this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order", "order_Id", orderId));
		
		
		return this.modelmapper.map(order, OrderDto.class);
	}
	
	
	// create am order
	@Override
	public OrderDto createOrder(Integer userId, OrderDto OrderDto) {
		
		Order order =  this.modelmapper.map(OrderDto, Order.class);
		
		Order new_order = this.orderRepo.save(order);
			
		return this.modelmapper.map(new_order, OrderDto.class);
	}

	
	// delete an order
	@Override
	public void deleteOrder(Integer orderId) {
		
		this.orderRepo.deleteById(orderId);
		
	
	}


	// get all the orders
	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> orders = this.orderRepo.findAll();
		List<OrderDto> orderDtos = orders.stream().map((order)->this.modelmapper.map(order, OrderDto.class)).collect(Collectors.toList());
		
		return orderDtos;
	}


	// update an order status : ADMIN 
	@Override
	public OrderDto updateOrderStatus(Integer orderId, String status) {
		Order order = this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","order_id",orderId));
		order.setStatus(status);
		this.orderRepo.save(order);
		return this.modelmapper.map(order, OrderDto.class);
	}


	@Override
	public List<OrderDto> findByAddress(String addr) {
		
		List<Order> orders = this.orderRepo.findByAddressIgnoreCase(addr);
		
		List<OrderDto> orderDtos = orders.stream().map((item)-> this.modelmapper.map(item, OrderDto.class)).collect(Collectors.toList());
		
		return orderDtos;
	}


	@Override
	public List<OrderDto> findByStatus(String status) {
	
List<Order> orders = this.orderRepo.findByStatusIgnoreCase(status);
		
		List<OrderDto> orderDtos = orders.stream().map((item)-> this.modelmapper.map(item, OrderDto.class)).collect(Collectors.toList());
		
		return orderDtos;
	}


	@Override
	public List<OrderDto> findByPriceBetween(Integer v1, Integer v2) {
		
List<Order> orders = this.orderRepo.findByPriceBetween(v1,v2);
		
		List<OrderDto> orderDtos = orders.stream().map((item)-> this.modelmapper.map(item, OrderDto.class)).collect(Collectors.toList());
		
		return orderDtos;
		
		
	}

}
