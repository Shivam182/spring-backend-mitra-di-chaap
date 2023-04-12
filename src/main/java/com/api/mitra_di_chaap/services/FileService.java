package com.api.mitra_di_chaap.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	// upload an image
	String uploadImage(String path, MultipartFile file)throws IOException;
	
	
	
	InputStream getResource(String path, Integer itemId)throws FileNotFoundException;
	
}
