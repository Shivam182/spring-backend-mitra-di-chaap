package com.api.mitra_di_chaap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MitraDiChaapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitraDiChaapApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
