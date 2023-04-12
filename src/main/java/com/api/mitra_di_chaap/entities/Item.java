package com.api.mitra_di_chaap.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="food_items")
@Getter
@Setter
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer itemId;
	
	@Column(name="item_name", nullable=false, length= 100)
	private String title;
	
	@Column(length=1000)
	private String description;
	
	private Integer price;
	
	private String imageName;
	
	
	// Bi directional Mapping
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	
	@ManyToMany
	@JoinColumn(name="cartId")
	private List<Cart> carts;
	
	@OneToMany(mappedBy="foodItem")
	private Set<Reviews> reviews = new HashSet<>();
	
	@ManyToOne
	private User user;
	
}
