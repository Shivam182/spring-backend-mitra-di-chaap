package com.api.mitra_di_chaap.entities;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
	
	
	@Id  // userid.itemid
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	
	private String status;
	private Integer price;
	private String address;
	private String email;
	
	private String orderedOn;
	
	@ElementCollection
	private Map<Integer,Integer> items = new HashMap<>();
	
}
