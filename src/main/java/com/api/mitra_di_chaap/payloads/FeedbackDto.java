package com.api.mitra_di_chaap.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackDto {
	
	private int Id;
	
	private String content;
	
	private int userId;
	
	private String userMail;
	
	private String name;
	
}
