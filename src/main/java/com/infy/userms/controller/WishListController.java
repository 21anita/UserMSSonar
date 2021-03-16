package com.infy.userms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.userms.dto.WishListDTO;
import com.infy.userms.service.WishListService;


@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class WishListController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WishListService wishListService;
	@Autowired 
	Environment environment;
	// add to wishlist
	@PostMapping(value = "/wishlist/add")
	public ResponseEntity<String> AddToWish(@RequestBody WishListDTO wishListDTO)throws Exception
	{
		logger.info("Adding to wishlist{}", wishListDTO.getBuyerId());
		ResponseEntity<String> response = null;
		try
		{
		wishListService.AddToWishList(wishListDTO);
		response = new ResponseEntity<String>(environment.getProperty("ADD_TO_CART"), HttpStatus.OK);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.OK,environment.getProperty("WISHLIST_EXISTS"));
				 
		}
		return response;
	}
	
	//get details from wishlist
	@GetMapping(value="/wishlistdata",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<WishListDTO> getWishListData()
	{
		logger.info("Fetching wishlist data");
		return wishListService.getWishList();
	}

	//delete from wishlist
	@DeleteMapping(value = "/wishlist/remove")	
	public ResponseEntity<String> deleteWish(@RequestBody WishListDTO wishListDTO)throws Exception
	{
	
		logger.info("Deleting wishlist item");
		ResponseEntity<String> response = null;
		//System.out.println(wishListDTO.getBuyerId());
		//System.out.println(wishListDTO.getProdId());
		boolean status = wishListService.deleteWishListItem(wishListDTO);
		System.out.println(status);
		if(status) {
			response = new ResponseEntity<String>(environment.getProperty("DELETION_WISHLIST_ITEM"),HttpStatus.OK);
		 }else {
		     response = new ResponseEntity<String>(environment.getProperty("ITEM_DOES_NOT_EXIST"),HttpStatus.BAD_REQUEST);
		 }
		 return response;

	}
	//Adds wish list to the cart
	@PostMapping(value = "/wishlist/cart")
	public ResponseEntity<String> addFromWishListToCart(@RequestBody WishListDTO wishListDTO){
		 ResponseEntity<String> response = null;
		 try {
		 wishListService.addFromWishToCart(wishListDTO);
		 response = new ResponseEntity<String>(environment.getProperty("ADDED_WISH_LIST_TO_CART"),HttpStatus.OK);
		 }catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
				 
		}
		 return response;
	}
	

}
