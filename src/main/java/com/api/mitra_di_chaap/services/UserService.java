package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.UserDto;

public interface UserService {
	UserDto registerUser(UserDto user);
	
	UserDto createUser(UserDto user);
	
	
	UserDto updateuser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	
	List<UserDto> getAllUsers();
	
	
	void deleteUser(Integer userId);	
	
	
}
