package com.api.mitra_di_chaap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException,IOException {
		
		// 1.get token from the Header
		String requestToken = request.getHeader("Authorization");
		
		System.out.println("Token sent by user for validation: "+ requestToken);
		
		
		String username = null;
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			
			// remove "Bearer" from the token
			token = requestToken.substring(7);
			
			try {
				
				username = this.jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
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
//				System.out.println("here...");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid jwt token");
			}
//				System.out.println("username: "+username);
		}else {
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
//		System.out.println(response);
	}
}
