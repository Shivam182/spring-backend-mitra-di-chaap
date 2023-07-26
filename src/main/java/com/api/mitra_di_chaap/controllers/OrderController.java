package com.api.mitra_di_chaap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.payloads.ApiResponse;
import com.api.mitra_di_chaap.payloads.OrderDto;
import com.api.mitra_di_chaap.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	
	
	// create an order 
	@PostMapping("/new/{userId}")
	public ResponseEntity<OrderDto> createOrder(@PathVariable Integer userId, @RequestBody OrderDto orderDto){
		
		OrderDto order =  this.orderService.createOrder(userId, orderDto);
		
		
		return new ResponseEntity<OrderDto>(order, HttpStatus.OK);
	}
	
	
	// get all orders : ADMIN
	@GetMapping("/all")
	public ResponseEntity<List<OrderDto>> getAllOrders(){
		
		List<OrderDto> orders = this.orderService.getAllOrders();
		
		return new ResponseEntity<List<OrderDto>>(orders, HttpStatus.OK);
	}
	
	
	// access an order details 
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId){
		
		OrderDto orderDto =  this.orderService.getOrder(orderId);
		
		
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
		
	}
	
	
	// update order status : ADMIN
	@PutMapping("/updateStatus/{orderId}")
	public ResponseEntity<OrderDto> updateStatus(@PathVariable Integer orderId, @RequestBody String status){
//		System.err.println(status);
		OrderDto orderDto =  this.orderService.updateOrderStatus(orderId, status);
		
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
	}
	
	
	// deleteOrder : ADMIN / SELF cancel order 
	@DeleteMapping("/{orderId}")
	public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Integer orderId){
		
		this.orderService.deleteOrder(orderId);
		
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order has been cancelled successfully.",true), HttpStatus.OK);
		
	}
}