package com.api.mitra_di_chaap.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	
	
	private String password;
}
