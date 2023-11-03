package com.api.mitra_di_chaap.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="carts")
@Getter
@Setter
@NoArgsConstructor
public class Cart {	
	
	
	// assign cartId same as user id.
	
	@Id
	private Integer cartId;	
	
	private Integer total;
	
//	private int count;
	
	
//	@OneToMany(mappedBy="itemId")
	@ElementCollection
	private Map<Integer,Integer> food_item = new HashMap<>();

}
