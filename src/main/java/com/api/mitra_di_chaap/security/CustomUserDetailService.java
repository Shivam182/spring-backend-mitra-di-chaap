package com.api.mitra_di_chaap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mitra_di_chaap.entities.User;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by username
		User user  =this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User"," email :"+username,0) );
		return user;
	}
	
}
