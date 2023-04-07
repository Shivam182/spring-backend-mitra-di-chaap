package com.api.mitra_di_chaap.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.payloads.ApiResponse;
import com.api.mitra_di_chaap.payloads.CartDto;
import com.api.mitra_di_chaap.payloads.UserDto;
import com.api.mitra_di_chaap.services.CartService;
import com.api.mitra_di_chaap.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	
	
	
	@PreAuthorize("#id == authentication.principal.id")
	@PostMapping("/createCart")
	public ResponseEntity<CartDto> createUserCart(@Valid @RequestBody UserDto userDto){
		
//		UserDto created_user = this.userService.createUser(userDto);
		
			//create a cart 
		CartDto created_cart = this.cartService.createCart(userDto.getId());
		
		return new ResponseEntity<CartDto>(created_cart, HttpStatus.CREATED);
	}
	
	
	
	@PreAuthorize("#id == authentication.principal.id")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		UserDto updated_user = this.userService.updateuser(userDto, userId);
		return ResponseEntity.ok(updated_user);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
			
		this.userService.deleteUser(id);
		
		return new ResponseEntity(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		UserDto userDto = this.userService.getUserById(userId);
		
		return ResponseEntity.ok(userDto);
	}
	
	
}
