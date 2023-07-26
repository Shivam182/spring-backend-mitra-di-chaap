package com.api.mitra_di_chaap.services;

import com.api.mitra_di_chaap.payloads.OrderDto;
import java.util.List;

public interface OrderService {
	
	OrderDto getOrder(Integer orderId);
	OrderDto createOrder(Integer userId, OrderDto orderDto);
	void deleteOrder(Integer orderId);
	List<OrderDto> getAllOrders();
	OrderDto updateOrderStatus(Integer orderId, String status);
}
