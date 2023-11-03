package com.api.mitra_di_chaap.services;

import com.api.mitra_di_chaap.payloads.OrderDto;
import java.util.List;

public interface OrderService {
	
	OrderDto getOrder(Integer orderId);
	OrderDto createOrder(Integer userId, OrderDto orderDto);
	void deleteOrder(Integer orderId);
	List<OrderDto> getAllOrders();
	OrderDto updateOrderStatus(Integer orderId, String status);
	
	List<OrderDto> findByAddress(String addr);
	List<OrderDto> findByStatus(String status);
	List<OrderDto> findByPriceBetween(Integer v1,Integer v2);
	List<OrderDto> getOrdersByUserEmail(String email);
}
