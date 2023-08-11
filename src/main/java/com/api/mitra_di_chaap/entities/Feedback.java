package com.api.mitra_di_chaap.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="feedbacks")
@Getter
@Setter
@NoArgsConstructor
public class Feedback {
	
	@Id
	private int Id;
	
	private String content;
	
	private int userId;
	
	private String userMail;
	
	private String name;
	
}
