package com.api.mitra_di_chaap.services.impl;

import java.io.IOException;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.payloads.ItemDto;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.services.CloudinaryImageService;
import com.api.mitra_di_chaap.services.ItemService;
import com.cloudinary.Cloudinary;


@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

	
	@Autowired
	private Cloudinary cloudinary;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private ItemService itemService;
	
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public void upload(MultipartFile files[],Integer itemId) {
		
		ItemDto itemDto = this.itemService.getItemById(itemId);
		
		
		
		
		
		
		try {
			
			@SuppressWarnings("unchecked")
			Map<String,String> data0 = this.cloudinary.uploader().upload(files[0].getBytes(), null);
			
			itemDto.setImage1(data0.get("secure_url"));
			
			@SuppressWarnings("unchecked")
			Map<String,String> data1 = this.cloudinary.uploader().upload(files[1].getBytes(), null);
			
			itemDto.setImage2(data1.get("secure_url"));
			
			@SuppressWarnings("unchecked")
			Map<String,String> data2 = this.cloudinary.uploader().upload(files[2].getBytes(), null);
			
			itemDto.setImage3(data2.get("secure_url"));
			
			

			Item item = this.modelMapper.map(itemDto, Item.class);
			
			this.itemRepo.save(item);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
