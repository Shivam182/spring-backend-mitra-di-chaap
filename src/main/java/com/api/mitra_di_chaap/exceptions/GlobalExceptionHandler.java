package com.api.mitra_di_chaap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ApiException> resourceNotFoundException(ResourceNotFoundException exc){
		String message = exc.getMessage();
		
		
		ApiException apiResponse = new ApiException(message);
		
		return new ResponseEntity<ApiException>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
