package com.api.mitra_di_chaap;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


//@WebFilter(urlPatterns="/*")
//@Component
public class AddResponseHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		 HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		 	System.out.println("i ran ......................");
	        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
	        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Methods, Access-Control-Allow-Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
	        httpServletResponse.setHeader("Access-Control", null);
	        httpServletResponse.setHeader("Access-Control-Allow-Headers","Access-Control-Allow_Origin");
	        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
	        chain.doFilter(request, response);
	        
	        

	        
	        
	}

	

	
}
