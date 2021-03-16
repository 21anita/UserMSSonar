package com.infy.userms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userms.dto.WishListDTO;
import com.infy.userms.entity.Cart;
import com.infy.userms.entity.CompositeKey;
import com.infy.userms.entity.WishList;
import com.infy.userms.repository.CartRepository;
import com.infy.userms.repository.WishListRepository;

@Service
public class WishListService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WishListRepository wishListRepo;
	@Autowired
	CartRepository cartRepo;
	//Add to wishlist
		public void AddToWishList(WishListDTO wishListDTO)throws Exception
		{
			logger.info("Adding to wishlist {}", wishListDTO.getBuyerId());
			CompositeKey comp = new CompositeKey();
			comp.setBuyerId(wishListDTO.getBuyerId());
			comp.setProdId(wishListDTO.getProdId());
			WishList wishlist = wishListRepo.findById(comp).orElse(null);
			if(wishlist != null) {
				throw new Exception("WISHLIST_EXISTS");
			}
			WishList wishList = wishListDTO.createEntity();
			wishListRepo.save(wishList);
		}
			//get wishlist details
		public List<WishListDTO> getWishList()
		{
			logger.info("Wishlist details");

			List<WishList> wish = wishListRepo.findAll();
			List<WishListDTO> wishDTO = new ArrayList<>();
			for(WishList w : wish)
			{
				WishListDTO wishListDTO = WishListDTO.valueOf(w);
				wishDTO.add(wishListDTO);
			}
			return wishDTO;

		}
		
		//delete wishlist item
		public Boolean deleteWishListItem(WishListDTO wishListDTO)
		{
			logger.info("Deleting wishlist item");
			//System.out.println(wishListDTO.getBuyerId());
			CompositeKey comp = new CompositeKey();
			comp.setBuyerId(wishListDTO.getBuyerId());
			comp.setProdId(wishListDTO.getProdId());
			WishList wishlist = wishListRepo.findById(comp).orElse(null);
			//System.out.println(wishlist);
			if(wishlist != null)
			{
				wishListRepo.deleteById(comp);
				return true;
			}
			return false;
	
		}

		//add wishlist to the cart
		public void addFromWishToCart(WishListDTO wishListDTO) throws Exception {
			CompositeKey comp = new CompositeKey();
			comp.setBuyerId(wishListDTO.getBuyerId());
			comp.setProdId(wishListDTO.getProdId());
			Optional<WishList> wish = wishListRepo.findById(comp);
			Optional<Cart> cart = cartRepo.findById(comp);
			//Optional<WishList> wishList = wishListRepo.findById(new WishListEntityUsingIdClass(wishListDTO.getBuyerId(),wishListDTO.getProdId()));
			//Optional<CartEntity> optCart = cartRepo.findById(new CartEntityUsingIdClass(wishListDTO.getBuyerId(),wishListDTO.getProdId()));
			//System.out.println(optCart.isPresent());
			//System.out.println(optWishList.isPresent());
			if(cart.isPresent() && wish.isPresent()) {
				System.out.println("aadding wishlist to cart already present");

				WishList wishList = wish.get();
				Cart carts = cart.get();
				carts.setQuantity(carts.getQuantity()+1);
				wishListRepo.delete(wishList);
				cartRepo.save(carts);
			}
			else if(!wish.isPresent()) {
				System.out.println("aadding wishlist to cart  exception");

				throw new Exception("WISHLIST_IS_EMPTY");
			}
			else {
				System.out.println("aadding wishlist to cart");
				WishList wishList = wish.get();
				wishListRepo.delete(wishList);
			    Cart cart1 = new Cart();
			    cart1.setBuyerId(wishListDTO.getBuyerId());
			    cart1.setProdId(wishListDTO.getProdId());
			    cart1.setQuantity(1);
			    cartRepo.save(cart1);
			}
			
		}
		
}
