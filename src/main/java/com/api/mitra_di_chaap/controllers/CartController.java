package com.api.mitra_di_chaap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.entities.Cart;
import com.api.mitra_di_chaap.payloads.CartDto;
import com.api.mitra_di_chaap.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	
	@Autowired
	private CartService cartService;
	
	// add to cart
	@PreAuthorize("#cartId == authentication.principal.id")
	@PutMapping("/add/{cartId}/{itemId}")
	public ResponseEntity<CartDto> addToCart(@PathVariable Integer itemId, @PathVariable Integer cartId){	
		
		CartDto updated = this.cartService.addToCart(itemId, cartId);
		
		return new ResponseEntity<CartDto>(updated, HttpStatus.OK);
	}
	
	
	
		// delete an item from a cart
		@PreAuthorize("#cartId == authentication.principal.id")
		@PutMapping("/delete/{cartId}/{itemId}")
		public ResponseEntity<CartDto> deleteItem(@PathVariable Integer itemId, @PathVariable Integer cartId){
			
			CartDto updated = this.cartService.deleteFromCart(itemId, cartId);
			
			return new ResponseEntity<CartDto>(updated, HttpStatus.OK);
		}
		
		
		
		
		// get a cart by id : accessible to owner user & ADMIN
		@PreAuthorize("hasAuthority('ADMIN')or #cartId == authentication.principal.id")
		@GetMapping("/{cartId}")
		public ResponseEntity<CartDto> getCart(@PathVariable Integer cartId){
			
			CartDto cart = this.cartService.getCartById(cartId);
			
			
			
			return new ResponseEntity<CartDto>(cart,HttpStatus.OK);
		}
		
	
	
	
}
