package com.api.mitra_di_chaap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mitra_di_chaap.entities.Category;
import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.entities.User;

public interface ItemRepo extends JpaRepository<Item, Integer> {
	
	List<Item> findByCategory(Category ctaegory);
	
	List<Item> findByTitleContaining(String title);
	
//	List<Item> findByUser(User user);
}
