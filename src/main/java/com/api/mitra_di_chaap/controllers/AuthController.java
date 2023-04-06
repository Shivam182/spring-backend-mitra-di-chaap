package com.api.mitra_di_chaap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mitra_di_chaap.exceptions.ApiException;
import com.api.mitra_di_chaap.payloads.JwtAuthRequest;
import com.api.mitra_di_chaap.payloads.JwtAuthResponse;
import com.api.mitra_di_chaap.payloads.UserDto;
import com.api.mitra_di_chaap.security.JwtTokenHelper;
import com.api.mitra_di_chaap.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Autowired
	private UserService userService;
	
	
	// this gives a token which can be used in every consecutive requests.
	
	@PostMapping("/createToken")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		
		
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		
//		System.out.println("helllo................");

		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("invalid user credentials");
			throw new ApiException("Invalid user credentials");
			
		}
		
		
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registeredUser = this.userService.registerUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
}