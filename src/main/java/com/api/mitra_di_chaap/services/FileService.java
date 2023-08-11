package com.api.mitra_di_chaap.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	// upload an image
	void uploadImage( MultipartFile[] files,Integer itemId)throws IOException;
	
	
	
//	String getResource( Integer itemId)throws FileNotFoundException;
	
}
