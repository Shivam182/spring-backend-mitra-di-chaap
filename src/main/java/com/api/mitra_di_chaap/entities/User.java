package com.api.mitra_di_chaap.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {


	// basic data
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int Id;
	private String name;
	private String email;
	private String password;
	
	
	// cart-items
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Item> food_items = new ArrayList<>();
	
	
	// user role : customer Or operator, an operator can delete user.
	private Set<Role> roles = new HashSet<>();
	
}
