package com.api.mitra_di_chaap.payloads;

import java.util.Collection;

import lombok.Data;

@Data
public class JwtAuthResponse {
	
	private String token;
	
	private Collection<?> role;
}
