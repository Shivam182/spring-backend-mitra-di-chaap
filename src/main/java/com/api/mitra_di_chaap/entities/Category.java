package com.api.mitra_di_chaap.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name="title", nullable=false, length=100)
	private String categoryTitle;
	
	@Column(name="description")
	private String categoryDescription;
	
	// one to many mapping : one category can have many food_items, This technically means one row of this table is mapped to multiple rows of another table.
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Item> food_items = new ArrayList<>();
	
	
}
