package com.api.mitra_di_chaap.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


@Service
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
	public ItemDto addToCart(Integer itemId, Integer cartId) {
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", cartId));
		
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		
//		this.cartRepo.save(item);
		
		item.setCart(cart);
		
		
		
		return this.modelMapper.map(item, ItemDto.class);
	}
	
	
	
	@Override
	public ItemDto createItem(ItemDto itemDto, Integer categoryId) {
		
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		
		Item item = this.modelMapper.map(itemDto, Item.class);
		
		
		item.setImageName("default.png");
		item.setCategory(category);
		
		
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

	
	


	// gets all items of a user cart
	@Override
	public List<ItemDto> getItemsByUser(Integer cartId) {
		
//		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("User","user id",cartId));
//		
//		List<Integer> ids = cart.getFood_item();
		
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
