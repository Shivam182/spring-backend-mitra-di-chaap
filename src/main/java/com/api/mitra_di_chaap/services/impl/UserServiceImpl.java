package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.User;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.UserDto;
import com.api.mitra_di_chaap.repositories.UserRepo;
import com.api.mitra_di_chaap.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public UserDto registerUser(UserDto user) {
		return null;
	}

	
	// TODO: check if the user already exists before registering them !!!
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		
		User saved_user = this.userRepo.save(user);
		
		
		return this.userToDto(saved_user);
	}

	
	@Override
	public UserDto updateuser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User updated_user = this.userRepo.save(user);
		
		return this.userToDto(updated_user);
	}
	
	

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map((user)->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		this.userRepo.delete(user);
	}
	
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
////		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
		
	}
	
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
////		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

}
