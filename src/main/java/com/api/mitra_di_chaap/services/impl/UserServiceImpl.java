package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.config.AppConstants;
import com.api.mitra_di_chaap.entities.Role;
import com.api.mitra_di_chaap.entities.User;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.UserDto;
import com.api.mitra_di_chaap.repositories.RoleRepo;
import com.api.mitra_di_chaap.repositories.UserRepo;
import com.api.mitra_di_chaap.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		
		// encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// role assign : normal user
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

	
	// TODO: Redundant method !!!!
//	@Override
//	public UserDto createUser(UserDto userDto) {
//		User user = this.dtoToUser(userDto);
//		
//		User saved_user = this.userRepo.save(user);
//		
//		
//		return this.userToDto(saved_user);
//	}

	
	@PreAuthorize("#userId == authentication.principal.id")
	@Override
	public UserDto updateuser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		
		User updated_user = this.userRepo.save(user);
		
		return this.userToDto(updated_user);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
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

	@PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
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
//		user.setAbout(userDto.getAbout());
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


	@Override
	public List<UserDto> findByName(String name) {
		
		List<User> users = this.userRepo.findByNameIgnoreCase(name);
		
		List<UserDto> userDtos = users.stream().map((item)-> this.modelMapper.map(item, UserDto.class)).collect(Collectors.toList());
		
		
		
		return userDtos;
	}


	@PreAuthorize("hasAuthority('ADMIN') or #email == authentication.principal.email")
	@Override
	public UserDto findUserByEMail(String email) {
		User user = this.userRepo.findUserByEmail(email);
		return this.modelMapper.map(user, UserDto.class);
	}

	
	@PreAuthorize("#userId == authentication.principal.id")
	@Override
	public void updatePassword(Integer userId, UserDto userDto) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user_id",userId));
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		this.userRepo.save(user);
	}

}
