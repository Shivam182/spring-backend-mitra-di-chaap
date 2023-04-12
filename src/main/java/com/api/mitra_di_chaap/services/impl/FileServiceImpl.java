package com.api.mitra_di_chaap.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.mitra_di_chaap.entities.Item;
import com.api.mitra_di_chaap.exceptions.ResourceNotFoundException;
import com.api.mitra_di_chaap.repositories.ItemRepo;
import com.api.mitra_di_chaap.services.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private ItemRepo itemRepo;

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
		
		String randomID  = UUID.randomUUID().toString();
		
		String filename1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		String filepath = path + File.separator + filename1;
		
		// create new folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		// file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		return filename1;
	}
	
	

	@Override
	public InputStream getResource(String path, Integer itemId) throws FileNotFoundException {
		Item item = this.itemRepo.findById(itemId).orElseThrow(()-> new ResourceNotFoundException("Item","item id",itemId));
		String filename = item.getImageName();
		String fullPath = path + File.separator + filename;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
