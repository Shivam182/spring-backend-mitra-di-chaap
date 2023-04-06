package com.api.mitra_di_chaap.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.api.mitra_di_chaap.services.FileService;
import com.api.mitra_di_chaap.services.ItemService;



@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CartService cartService;
	
	
	@Autowired
	private FileService fileService;
	
	
	// here the images folder in our server program only acts as the database for serving/storing the images.
	@Value("${project.image}")
	private String path;
	
		// create
	
		@PostMapping("/item/category/{categoryId}")
		public ResponseEntity<ItemDto> createItem( @RequestBody ItemDto itemDto, @PathVariable Integer categoryId){
			
		ItemDto createPost = this.itemService.createItem(itemDto, categoryId);
		
		return new ResponseEntity<ItemDto>(createPost,HttpStatus.CREATED);	
		}
		
		
		
		// get by user
		@GetMapping("/user/{userId}/items")
		public ResponseEntity<List<ItemDto>> getItemsByUser(@PathVariable Integer userId){
			
			CartDto cartDto = this.cartService.getCartById(userId);
			
			// get food item ids
			List<Integer> ids = cartDto.getFood_item();
			
			// get items using food ids
			List<ItemDto> cartItems = ids.stream().map((id)-> this.itemService.getItemById(id)).collect(Collectors.toList());	
			
			
			return new ResponseEntity<List<ItemDto>>(cartItems,HttpStatus.OK);
			
		}
		
		
		
		// get by category
		@GetMapping("/category/{categoryId}/items")
		public ResponseEntity<List<ItemDto>> getPostByCategory(@PathVariable Integer categoryId){
			
			List<ItemDto> posts = this.itemService.getItemsByCategory(categoryId);
			return new ResponseEntity<List<ItemDto>>(posts,HttpStatus.OK);
			
		}
		
		
		
		// get all items
		@GetMapping("/items")
		public ResponseEntity<ItemResponse> getAllPost(@RequestParam(value= "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value="pageSize",defaultValue= AppConstants.PAGE_SIZE,required=false) Integer pageSize , @RequestParam(value = "sortBy", defaultValue=AppConstants.SORT_BY,required= false) String sortBy, @RequestParam(value= "sortDir",defaultValue=AppConstants.SORT_DIR,required= false) String sortDir){
			
			ItemResponse postResposne = this.itemService.getAllItems(pageNumber,pageSize,sortBy,sortDir);
			
			return new ResponseEntity<ItemResponse>(postResposne,HttpStatus.OK);
		}
		
		
		// get item by Id
		@GetMapping("/item/{itemId}")
		public ResponseEntity<ItemDto> getPostById(@PathVariable Integer itemId){
			
			ItemDto itemDto = this.itemService.getItemById(itemId);
			
			return new ResponseEntity<ItemDto>(itemDto,HttpStatus.OK);
		}
		
		
		
		
		
		// delete post
		@DeleteMapping("/item/{itemId}")
		public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer itemId) {
			
			this.itemService.deleteItem(itemId);
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted.",true), HttpStatus.OK);
					
		}
		
		
		
		// update post 
		@PutMapping("/item/{itemId}")
		public ResponseEntity<ItemDto> updatePost(@RequestBody ItemDto itemDto, @PathVariable Integer itemId ){
			
			ItemDto updatePost = this.itemService.updateItem(itemDto, itemId);
			return new ResponseEntity<ItemDto>(updatePost,HttpStatus.OK);
		}
		
		
		// search 
		@GetMapping("items/search/{keywords}")
		public ResponseEntity<List<ItemDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
			List<ItemDto> result = this.itemService.searchItems(keywords);
			
			return new ResponseEntity<List<ItemDto>>(result,HttpStatus.OK);
		}
		
		
		// post image upload
		@PostMapping("/item/image/upload/{itemId}")
		public ResponseEntity<ItemDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer itemId) throws IOException{
			ItemDto postDto = this.itemService.getItemById(itemId);
			String filename = this.fileService.uploadImage(path, image);
			

			
			postDto.setImageName(filename);
			ItemDto updatedItem = this.itemService.updateItem(postDto, itemId);
			
			return new ResponseEntity<ItemDto>(updatedItem,HttpStatus.OK); 
		}
		
		// method to serve files
		@GetMapping(value="/item/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException{
			InputStream resource = this.fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
		}
		
		
		
		
		
	}
	
	


