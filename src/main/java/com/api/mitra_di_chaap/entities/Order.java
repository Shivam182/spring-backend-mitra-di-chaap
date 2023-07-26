package com.api.mitra_di_chaap.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
	private Integer orderId;
	
	
	private String status;
	private Integer price;
	private String address;
	
	@ElementCollection
	private Map<Integer,Integer> items = new HashMap<>();
	
}
