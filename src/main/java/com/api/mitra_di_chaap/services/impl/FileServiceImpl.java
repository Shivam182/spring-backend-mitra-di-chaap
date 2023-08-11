package com.api.mitra_di_chaap.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.payloads.ItemDto;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.services.FileService;
import com.api.mitra_di_chaap.services.ItemService;
import com.mysql.cj.util.StringUtils;

import utils.ImageUtils;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void uploadImage(MultipartFile[] files,Integer itemId) throws IOException {
		
		ItemDto itemDto = this.itemService.getItemById(itemId);
		
		
		
		int i = 0;
		for(MultipartFile file:files) {
			
			
			
		}
		
		
		Item item = this.modelMapper.map(itemDto, Item.class);
		
		this.itemRepo.save(item);
		
//		String name = file.getOriginalFilename();
//		
//		String randomID  = UUID.randomUUID().toString();
//		
//		String filename1 = randomID.concat(name.substring(name.lastIndexOf(".")));
//		
//		String filepath = path + File.separator + filename1;
		
		// create new folder if not created
//		File f = new File(path);
//		if(!f.exists()) {
//			f.mkdir();
//		}
		
		// file copy
//		Files.copy(file.getInputStream(), Paths.get(filepath));
		
//		return filename1;
	}
	
	

//	@Override
//	public String getResource(Integer itemId) throws FileNotFoundException {
//		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
//		String filename = item.getMainImage();
////		String fullPath = path + File.separator + filename;
////		InputStream is = new FileInputStream(fullPath);
//		return filename;
//	}

}
