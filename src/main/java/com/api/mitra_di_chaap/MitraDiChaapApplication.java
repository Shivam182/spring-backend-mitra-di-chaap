package com.api.mitra_di_chaap;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.api.mitra_di_chaap.config.AppConstants;
import com.api.mitra_di_chaap.entities.Role;
import com.api.mitra_di_chaap.repositories.RoleRepo;

//@ServletComponentScan
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
	
	

	
//	@Override
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
