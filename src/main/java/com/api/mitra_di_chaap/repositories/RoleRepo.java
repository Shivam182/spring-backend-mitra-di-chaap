package com.api.mitra_di_chaap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mitra_di_chaap.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	
}
