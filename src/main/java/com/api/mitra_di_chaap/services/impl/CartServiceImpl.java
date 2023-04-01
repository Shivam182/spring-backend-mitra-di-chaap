package com.api.mitra_di_chaap.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.Cart;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.CartDto;
import com.api.mitra_di_chaap.repositories.CartRepo;
import com.api.mitra_di_chaap.services.CartService;


@Service
public class CartServiceImpl implements CartService {
	
	
	@Autowired
	private CartRepo cartRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	


	@Override
	public CartDto addToCart(Integer itemId, Integer cartId) {
		
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart", "cart id", cartId));
		
		List<Integer> items = cart.getFood_item();
		items.add(itemId);
		
		cart.setFood_item(items);
		cart.setTotal(55);
		
		this.cartRepo.save(cart);
		
		CartDto cartDto = this.modelMapper.map(cart, CartDto.class);
		return cartDto;
	}

	@Override
	public CartDto deleteFromCart(Integer itemId, Integer cartId) {
		
		Cart cart  = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart", "cart id", cartId));
		
		cart.getFood_item().remove(itemId);
		
		
		this.cartRepo.save(cart);
		
		
		return this.modelMapper.map(cart, CartDto.class);
		
		
	}

	
	// creates an empty cart 
	@Override
	public CartDto createCart(Integer userId) {
		Cart cart = new Cart();
		cart.setCartId(userId);
		cart.setFood_item(null);
		cart.setTotal(0);
		
		this.cartRepo.save(cart);
	
		return this.modelMapper.map(cart, CartDto.class);
	}

	
	
	@Override
	public CartDto getCartById(Integer userId) {
		
		Cart myCart = this.cartRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		
		
		
		return this.modelMapper.map(myCart, CartDto.class);
	}

}
