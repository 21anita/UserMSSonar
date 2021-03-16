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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infy.userms.dto.CartDTO;
import com.infy.userms.entity.CompositeKey;
import com.infy.userms.service.CartService;


@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class CartController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CartService cartService;
	@Autowired 
	Environment environment;
	//add to cart
		@PostMapping(value = "/cart/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> AddToCart(@RequestBody CartDTO cartDTO)throws Exception
		{
			logger.info("Adding to cart{}", cartDTO);
			ResponseEntity<String> response = null;
			try
			{
			cartService.AddToTheCart(cartDTO);
			response = new ResponseEntity<String>(environment.getProperty("ADD_TO_CART"), HttpStatus.OK);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
				 
			}
			return response;

		}
		//get all cart details
		@GetMapping(value="/cartdata",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<CartDTO> getCartData()
		{
			logger.info("Fetching Cart data");
			return cartService.getCartDetails();
		}
		
		//get specific cart item
		@GetMapping(value = "/cart/{buyerid}/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public CartDTO getSpecificCartItemDetails(@PathVariable Integer buyerid, @PathVariable Integer prodid) {
			logger.info("Fetching specific cart item details {}");
			CompositeKey comp = new CompositeKey();
			comp.setBuyerId(buyerid);
			comp.setProdId(prodid);
			return cartService.getSpecificCartItem(comp);
		}

		//delete cart item
		@DeleteMapping(value = "/cart/remove")	
		public ResponseEntity<String> deleteCart(@RequestBody CartDTO cartDTO)
		{
			logger.info("Deleting cart item");
			ResponseEntity<String> response = null;
			boolean status = cartService.deleteCartItem(cartDTO);
			if(status) {
				response = new ResponseEntity<String>(environment.getProperty("DELETION_CART_ITEM"),HttpStatus.OK);
			 }else {
			     response = new ResponseEntity<String>(environment.getProperty("ITEM_DOES_NOT_EXIST"),HttpStatus.BAD_REQUEST);
			 }
			 return response;
		}
/*
		//removal of product from wishlist
		@PostMapping(value = "/wishlistremove", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void deleteFromWishList(@RequestBody WishListDTO wishListDTO)
		{
		String wishURI="http://localhost:8200/api/wishlist/remove";
		WishListDTO wish = new WishListDTO();
		wish.setBuyerId(wishListDTO.getBuyerId());
		wish.setProdId(wishListDTO.getProdId());
		System.out.println(wish.getBuyerId());
		
		System.out.println("after url");
		new RestTemplate().delete(wishURI,wish,String.class);
		//System.out.println(str);
		}

*/
}
