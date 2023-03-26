package com.api.mitra_di_chaap.exceptions;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {

	public ApiException(String message) {
		super(message);
		
	}
		
	
	public ApiException() {
		super();
	}
	
	
}
