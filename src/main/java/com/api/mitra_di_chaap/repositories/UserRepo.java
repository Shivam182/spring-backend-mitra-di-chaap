package com.api.mitra_di_chaap.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mitra_di_chaap.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
	
	List<User> findByNameIgnoreCase(String name);
	
}
