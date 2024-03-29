package com.api.mitra_di_chaap.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.mitra_di_chaap.config.AppConstants;
import com.api.mitra_di_chaap.payloads.ApiResponse;
import com.api.mitra_di_chaap.payloads.CartDto;
import com.api.mitra_di_chaap.payloads.ItemDto;
import com.api.mitra_di_chaap.payloads.ItemResponse;
import com.api.mitra_di_chaap.services.CartService;
import com.api.mitra_di_chaap.services.CloudinaryImageService;
import com.api.mitra_di_chaap.services.FileService;
import com.api.mitra_di_chaap.services.ItemService;



@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	

	
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private CloudinaryImageService cloudinaryImageService;
	
	
	// here the images folder in our server program only acts as the database for serving/storing the images.
	@Value("${project.image}")
	private String path;
	
		// create : only admin accessible 
		@PreAuthorize("hasAuthority('ADMIN')")
		@PostMapping("/item/category/{categoryId}")
		public ResponseEntity<ItemDto> createItem( @RequestBody ItemDto itemDto, @PathVariable Integer categoryId){
			
//		System.out.println("IMAGES: "+  itemDto.getImage1());
			
		ItemDto createPost = this.itemService.createItem(itemDto, categoryId);
		
		return new ResponseEntity<ItemDto>(createPost,HttpStatus.CREATED);	
		}
		
		
		
		// get by cartUId
//		@PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.id")
//		@GetMapping("/user/{userId}/items")
//		public ResponseEntity<List<ItemDto>> getItemsByUser(@PathVariable Integer userId){
//			
//			CartDto cartDto = this.cartService.getCartById(userId);
//			
//			// get food item ids
//			List<Integer> ids = cartDto.getFood_item();
//			
//			// get items using food ids
//			List<ItemDto> cartItems = ids.stream().map((id)-> this.itemService.getItemById(id)).collect(Collectors.toList());	
//			
//			
//			return new ResponseEntity<List<ItemDto>>(cartItems,HttpStatus.OK);
//			
//		}
		
		
		
		// get by category : accessible to everyone
		@GetMapping("/category/{categoryId}/items")
		public ResponseEntity<List<ItemDto>> getItemByCategory(@PathVariable Integer categoryId){
			
			List<ItemDto> posts = this.itemService.getItemsByCategory(categoryId);
			return new ResponseEntity<List<ItemDto>>(posts,HttpStatus.OK);
			
		}
		
		@GetMapping("/query/{query}")
		public ResponseEntity<List<ItemDto>> getItemByName(@PathVariable String query){
			
			List<ItemDto> items = this.itemService.findByTitleContaining(query);
			
			return new ResponseEntity<List<ItemDto>>(items,HttpStatus.OK);
		}
		
		
		@GetMapping("/item/price/{v}/{v1}")
		public ResponseEntity<List<ItemDto>> getItemsByPrice(@PathVariable Integer v, @PathVariable Integer v1){
			List<ItemDto> items = this.itemService.findByPrice(v,v1);
			
			return new ResponseEntity<List<ItemDto>>(items,HttpStatus.OK);
		}
		
		
		
		// get all items : accessible to all
		@GetMapping("/items")
		public ResponseEntity<ItemResponse> getAllItems(@RequestParam(value= "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value="pageSize",defaultValue= AppConstants.PAGE_SIZE,required=false) Integer pageSize , @RequestParam(value = "sortBy", defaultValue=AppConstants.SORT_BY,required= false) String sortBy, @RequestParam(value= "sortDir",defaultValue=AppConstants.SORT_DIR,required= false) String sortDir){
			
			ItemResponse postResposne = this.itemService.getAllItems(pageNumber,pageSize,sortBy,sortDir);
			
			return new ResponseEntity<ItemResponse>(postResposne,HttpStatus.OK);
		}
		
		
		// get item by Id: accessible to everyone 
		@GetMapping("/item/get/{itemId}")
		public ResponseEntity<ItemDto> getPostById(@PathVariable Integer itemId){
			
			ItemDto itemDto = this.itemService.getItemById(itemId);
			
			return new ResponseEntity<ItemDto>(itemDto,HttpStatus.OK);
		}
		
		
		
		
		
		// delete item: only admin accessible
		@PreAuthorize("hasAuthority('ADMIN')")
		@DeleteMapping("/item/{itemId}")
		public ResponseEntity<ApiResponse> deleteItem(@PathVariable Integer itemId) {
			
			this.itemService.deleteItem(itemId);
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted.",true), HttpStatus.OK);
					
		}
		
		
		
		// update an item: only admin accessible 
		@PreAuthorize("hasAuthority('ADMIN')")
		@PutMapping("/item/{itemId}")
		public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto, @PathVariable Integer itemId ){
			
			ItemDto updatePost = this.itemService.updateItem(itemDto, itemId);
			return new ResponseEntity<ItemDto>(updatePost,HttpStatus.OK);
		}
		
		
		// search : to all accessible 
		@GetMapping("items/search/{keywords}")
		public ResponseEntity<List<ItemDto>> searchItemByTitle(@PathVariable("keywords") String keywords){
			List<ItemDto> result = this.itemService.searchItems(keywords);
			
			return new ResponseEntity<List<ItemDto>>(result,HttpStatus.OK);
		}
		
		
		// post image upload: only admin accessible 
		@PreAuthorize("hasAuthority('ADMIN')")
		@PostMapping(value ="/item/image/upload/{itemId}")
		public ResponseEntity<String> uploadItemImage(@RequestParam MultipartFile[] image,@PathVariable Integer itemId) throws IOException{
			/*BHAI YE POSTMAN KE THROUGH SARE PARAMS UTHA LE RHA HAI , LEKIN BROWSER SE NAHI ???*/
			
			 this.cloudinaryImageService.upload(image,itemId);

				
			 return ResponseEntity.ok()
				      
				      .body("Response with header using ResponseEntity");}
		
		
		// method to serve files: all accessible 
//		@GetMapping(value="/item/image/{itemId}",produces = MediaType.IMAGE_JPEG_VALUE)
//		public void downloadImage(@PathVariable Integer itemId,HttpServletResponse response)throws IOException{
//			String resource = this.fileService.getResource(itemId);
//			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//			StreamUtils.copy(resource, response.getOutputStream());
//			
//			
//		}
		
	}