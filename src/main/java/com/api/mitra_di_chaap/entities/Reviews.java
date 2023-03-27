package com.api.mitra_di_chaap.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reviews")
@Getter
@Setter
public class Reviews {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String content;
	
	@ManyToOne
	private Item food_item;

}
