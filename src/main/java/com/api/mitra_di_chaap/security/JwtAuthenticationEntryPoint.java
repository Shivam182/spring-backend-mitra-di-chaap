package com.api.mitra_di_chaap.security;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint {
	
	public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException)throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied !!");
	}
	
}
