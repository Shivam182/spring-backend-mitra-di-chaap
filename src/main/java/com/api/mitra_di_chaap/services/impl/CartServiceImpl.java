package com.api.mitra_di_chaap.services.impl;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	

	@PreAuthorize("#cartId == authentication.principal.id")
	@Override
	public CartDto addToCart(Integer itemId, Integer cartId,Integer itemCount) {
		
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart", "cart id", cartId));
		
		Map<Integer,Integer> items = cart.getFood_item();
//		items.add(itemId);
		items.put(itemId, itemCount);
		
		cart.setFood_item(items);
		cart.setTotal(0);
		
		this.cartRepo.save(cart);
		
		CartDto cartDto = this.modelMapper.map(cart, CartDto.class);
		return cartDto;
	}

	
	@PreAuthorize("#cartId == authentication.principal.id")
	@Override
	public CartDto deleteFromCart(Integer itemId, Integer cartId) {
		
		Cart cart  = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart", "cart id", cartId));
		
		cart.getFood_item().remove(itemId);
		
		
		this.cartRepo.save(cart);
		
		
		return this.modelMapper.map(cart, CartDto.class);
		
		
	}

	
	// creates an empty cart 
	@PreAuthorize("#userId == authentication.principal.id")
	@Override
	public CartDto createCart(Integer userId) {
		Cart cart = new Cart();
		cart.setCartId(userId);
		cart.setFood_item(null);
		cart.setTotal(0);
		
		
		this.cartRepo.save(cart);
	
		return this.modelMapper.map(cart, CartDto.class);
	}

	
	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
	@Override
	public CartDto getCartById(Integer userId) {
		
		Cart myCart = this.cartRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		
		
		
		return this.modelMapper.map(myCart, CartDto.class);
	}
	
	
	@PreAuthorize("#cartId == authentication.principal.id")
	@Override
	public void updateCartItemCount(Integer cartId,Integer itemId, Integer itemCnt, Integer cart_total) {
			Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("cart", "cart id", cartId));
			
			cart.getFood_item().put(itemId, itemCnt);
			cart.setTotal(cart_total);
			
			this.cartRepo.save(cart);
	}

}
