package com.api.mitra_di_chaap.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	
	private int Id;
	
	@NotNull(message= "Please enter a name.")
	private String name;
	
	@Email(message="Please enter a valid email.")
	private String email;
	
	@NotNull(message="Please enter password.")
	private String password;
	
	
	

}
