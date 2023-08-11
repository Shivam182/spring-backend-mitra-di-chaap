package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.UserDto;

public interface UserService {
	UserDto registerUser(UserDto user);
	
//	UserDto createUser(UserDto user);
	
	
	UserDto updateuser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	
	List<UserDto> getAllUsers();
	
	// only for self and operator 
	void deleteUser(Integer userId);	
	
	List<UserDto> findByName(String name);
	
	
}
