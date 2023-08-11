package com.api.mitra_di_chaap.services;


import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {
	
	public void upload(MultipartFile files[],Integer itemId);
}
