package com.api.mitra_di_chaap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.api.mitra_di_chaap.config.AppConstants;
import com.api.mitra_di_chaap.entities.Role;
import com.api.mitra_di_chaap.repositories.RoleRepo;
import com.cloudinary.Cloudinary;

@SpringBootApplication
public class MitraDiChaapApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(MitraDiChaapApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Cloudinary getCloudinary() {
		
		Map<String, String> map = new HashMap<>();
		
		map.put("cloud_name", "dcxu9wsvj");
		map.put("api_key", "747139364576872");
		map.put("api_secret", "VdEq2Q8jTLw0qHNPQSa6Sl3X0m0");
		
		return new Cloudinary(map);
	}
	
	

	
	public void run(String... args) throws Exception {
		
		System.out.println(this.passwordEncoder.encode("passw"));
		
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("ADMIN_USER");
			
			List<Role> roles = List.of(role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);
			
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch(Exception e) {
			
		}
		
	}
	
	

}
