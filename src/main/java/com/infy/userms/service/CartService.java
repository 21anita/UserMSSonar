package com.infy.userms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userms.dto.CartDTO;
import com.infy.userms.entity.Cart;
import com.infy.userms.entity.CompositeKey;
import com.infy.userms.repository.CartRepository;


@Service
public class CartService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CartRepository cartRepo;
	
	//Add to cart
	public void AddToTheCart(CartDTO cartDTO)throws Exception
	{
		logger.info("Adding to cart {}", cartDTO);
		CompositeKey comp = new CompositeKey();
		comp.setBuyerId(cartDTO.getBuyerId());
		comp.setProdId(cartDTO.getProdId());
		Cart cart = cartRepo.findById(comp).orElse(null);
		if(cart != null) {
			throw new Exception("CART_EXISTS");
		}
		Cart carts = cartDTO.createEntity();
		cartRepo.save(carts);
	}
	//get cart details
		public List<CartDTO> getCartDetails()
		{
			logger.info("Cart details");

			List<Cart> cart = cartRepo.findAll();
			List<CartDTO> cartDTO = new ArrayList<>();
			for(Cart c : cart)
			{
				CartDTO cartDTO1 = CartDTO.valueOf(c);
				cartDTO.add(cartDTO1);
			}
			return cartDTO;

		}
		
		//delete cart item
		public Boolean deleteCartItem(CartDTO cartDTO)
		{
			logger.info("Deleting request for specific cart item");
			CompositeKey comp = new CompositeKey();
			comp.setBuyerId(cartDTO.getBuyerId());
			comp.setProdId(cartDTO.getProdId());
			Cart carts = cartRepo.findById(comp).orElse(null);
			if(carts != null)
			{
				cartRepo.deleteById(comp);
				return true;
			}
			return false;
		}
		
		//get specific cart item
		public CartDTO getSpecificCartItem(CompositeKey comp)
		{
			logger.info("Specific cart item ");
			CartDTO cart = null;
			Optional<Cart> p = cartRepo.findById(comp);
			if(p.isPresent())
			{
				Cart carts = p.get();
				cart = CartDTO.valueOf(carts);
			}
			return cart;
		}
		


}
