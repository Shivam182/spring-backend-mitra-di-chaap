package com.api.mitra_di_chaap.services;

import com.api.mitra_di_chaap.payloads.CartDto;

public interface CartService {
	
	
	
	CartDto createCart(Integer userId);
	
	CartDto getCartById(Integer userId);
	
	CartDto addToCart(Integer itemId, Integer cartId, Integer itemCount);
	
	CartDto deleteFromCart(Integer itemId, Integer cartId);
	
	
	
	

}
