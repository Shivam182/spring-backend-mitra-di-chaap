package com.api.mitra_di_chaap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter {
	
	private UserDetailsService userDetailsService;
	
	private JwtTokenHelper jwtTokenHelper;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException,IOException {
		
		// 1.get token
		String requestToken = request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		
		String username = null;
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			
			// remove "Bearer" from the token
			requestToken.substring(7);
			
			try {
				
				username = this.jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			}catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
		}else {
			System.out.println("Jwt token does not begin with 'Bearer'");
		}
		
		
		// once we get the token, now validated it 
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(null);
			}else {
				System.out.println("Invalid jwt token");
			}
				
		}else {
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}
}
