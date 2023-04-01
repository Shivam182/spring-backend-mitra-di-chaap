package com.api.mitra_di_chaap.services;

import java.util.List;

import com.api.mitra_di_chaap.payloads.ItemDto;

public interface ItemService {

	// create
	ItemDto createItem(ItemDto itemDto,  Integer categoryId);
	
	
	// update
	ItemDto updateItem(ItemDto itemDto, Integer itemId);
	
	// delete
	void deleteItem(Integer itemId);
	
	// add to cart
	ItemDto addToCart(Integer itemId, Integer cartId);
	
	// get all items
	
	
	// get item by id
	ItemDto getItemById(Integer itemId);
	
	
	// get all items by category
	List<ItemDto> getItemsByCategory(Integer categoryId);
	
	
	// get all items by user:cart
	List<ItemDto> getItemsByUser(Integer cartId);
	
	
	// get items by search
	List<ItemDto> searchItems(String keyword);
	
	
	
	

}
