package com.api.mitra_di_chaap.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PreAuthorize("#userDto.id == authentication.principal.id")
	@PutMapping("/createCart")
	public ResponseEntity<CartDto> createUserCart(@Valid @RequestBody UserDto userDto){
		
//		UserDto created_user = this.userService.createUser(userDto);
		
			//create a cart 
		CartDto created_cart = this.cartService.createCart(userDto.getId());
		
		return new ResponseEntity<CartDto>(created_cart, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		
		// encrypt the user password here 
		
//		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		
		UserDto updated_user = this.userService.updateuser(userDto, userId);
		return ResponseEntity.ok(updated_user);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
			
		this.userService.deleteUser(id);
		
		return new ResponseEntity(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		UserDto userDto = this.userService.getUserById(userId);

		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/name/{name}")
	public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable String name){
		List<UserDto> users = this.userService.findByName(name);
		
		
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable String email){
		
		UserDto userDto = this.userService.findUserByEMail(email);

		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	
	@PutMapping("/updatePassword")
	public ResponseEntity<ApiResponse> updatePassword( @RequestBody UserDto userDto){
		
		this.userService.updatePassword(userDto.getId(), userDto);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Password Updated Successfully", true), HttpStatus.OK);
	}
	
		
}
