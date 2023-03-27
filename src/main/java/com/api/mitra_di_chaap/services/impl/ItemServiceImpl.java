package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.mitra_di_chaap.entities.Cart;
import com.api.mitra_di_chaap.entities.Category;
import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.entities.User;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.ItemDto;
import com.api.mitra_di_chaap.repositories.CartRepo;
import com.api.mitra_di_chaap.repositories.CategoryRepo;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.repositories.UserRepo;
import com.api.mitra_di_chaap.services.ItemService;

public class ItemServiceImpl implements ItemService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@Override
	public ItemDto createItem(ItemDto itemDto, Integer userId, Integer categoryId) {
		
		Cart cart = this.cartRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Cart","cart id",userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		
		Item item = this.modelMapper.map(itemDto, Item.class);
		
		item.setImageName("default.png");
		item.setCategory(category);
		item.setCart(cart);
		
		Item newItem = this.itemRepo.save(item);
		
		
		return this.modelMapper.map(newItem, ItemDto.class);
	}
	
	

	@Override
	public ItemDto updateItem(ItemDto itemDto, Integer itemId) {
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		
		item.setTitle(itemDto.getTitle());
		item.setDescription(itemDto.getDescription());
		item.setImageName(itemDto.getImageName());
		
		return this.modelMapper.map(item, ItemDto.class);
	}

	
	
	@Override
	public void deleteItem(Integer itemId) {
		
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		
		this.itemRepo.delete(item);
	}

	
	
	@Override
	public ItemDto getItemById(Integer itemId) {
		
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		
		
		return this.modelMapper.map(item, ItemDto.class);
	}
	
	

	@Override
	public List<ItemDto> getItemsByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		List<Item> items = this.itemRepo.findByCategory(cat);
		List<ItemDto> itemDtos = items.stream().map((item)->this.modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
		
		
		return itemDtos;
	}

	
	
	// cartId same as userId.
	
	@Override
	public List<ItemDto> getItemsByUser(Integer cartId) {
		
		User user  = this.userRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("User","user id",cartId));
		List<Item> items = this.itemRepo.findByUser(user);
		List<ItemDto> itemDtos = items.stream().map((item)->this.modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
		
		return itemDtos;
	}

	
	
	@Override
	public List<ItemDto> searchItems(String keyword) {
		
		List<Item> items = this.itemRepo.findByTitleContaining(keyword);
		List<ItemDto> itemDtos = items.stream().map((item)->this.modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
		
		return itemDtos;
	}

}
