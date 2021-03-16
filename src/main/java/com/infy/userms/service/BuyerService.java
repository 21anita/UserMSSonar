package com.infy.userms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infy.userms.dto.BuyerDTO;
import com.infy.userms.dto.CartDTO;
import com.infy.userms.dto.LoginDTO;
import com.infy.userms.dto.OrderDetailsDTO;
import com.infy.userms.dto.ProductDTO;
import com.infy.userms.entity.Buyer;
import com.infy.userms.repository.BuyerRepository;
import com.infy.userms.validator.Validator;


@Service
public class BuyerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BuyerRepository buyerRepo;
	@Autowired
	Environment environment;
	//Register buyer
		public void createBuyer(Buyer buyer) throws Exception {
			logger.info("Creation request for buyer {}",buyer);
			Validator.validateBuyer(buyer);
			Optional<Buyer> phone = buyerRepo.findByPhonenumber(buyer.getPhonenumber());
			Optional<Buyer> email = buyerRepo.findByEmail(buyer.getEmail());
			
			if(email.isPresent()) {
				throw new Exception("EMAIL_ALREADY_EXISTS");
			}	
			if(phone.isPresent())
			{
				if(buyer.getIsActive() == 0) {
					throw new Exception("ACCOUNT_ALREADY_REGISTERED");
				}
				throw new Exception("PHONE_NUMBER_EXISTS");
			}
			buyer.setIsActive(1);
			buyerRepo.save(buyer);
		}
		//buyer login
	public boolean login(LoginDTO loginDTO) throws Exception{
		logger.info("Login request for buyer ", loginDTO.getEmail(),loginDTO.getPassword());
		List<Buyer> buyer = buyerRepo.findAll();
		for(Buyer b : buyer)
		{
			if(b.getEmail().equals(loginDTO.getEmail()) && b.getPassword().equals(loginDTO.getPassword()))
			{
				return true;
			}
			throw new Exception("INVALID_CREDENTIALS");
		}
		return false;

	}
	//get all buyers
	public List<BuyerDTO> getAllBuyers() throws Exception
	{
		logger.info("All Buyer details");
		List<BuyerDTO> buyerDTO = new ArrayList<>();
		try
		{
		List<Buyer> buyer = buyerRepo.findAll();
		if(buyer.isEmpty())
		{
			buyerDTO = null;
		}
		else
		{
		for(Buyer s : buyer)
		{
			BuyerDTO buyersDTO = BuyerDTO.valueOf(s);
			buyerDTO.add(buyersDTO);
		}
		}
		}catch (Exception e)
		{
			throw new Exception(environment.getProperty("NO_DATA"));
		}
		
		return buyerDTO;

	}
	
	//inactive buyer 
		public void InactiveBuyer(BuyerDTO buyerDTO)
		{
			logger.info("inactive buyer acc");
			Buyer buyer = buyerDTO.createEntity();
			buyerRepo.save(buyer);
			
		}


	//delete specific buyer
			public Boolean deleteSpecificBuyer(Integer buyerid)
			{
				Optional<Buyer> buyer = buyerRepo.findById(buyerid);
				if(buyer.isPresent())
				{
				buyerRepo.deleteById(buyerid);
				return true;
				}
				return false;
			}
	
	//get specific buyer
			public BuyerDTO getSpecificBuyer(Integer buyerid)
			{
				logger.info("Specific buyer details");
				BuyerDTO buyer = null; 
				Optional<Buyer> b = buyerRepo.findById(buyerid);
				if(b.isPresent())
				{
					Buyer buyers = b.get();
					buyer = BuyerDTO.valueOf(buyers); 
				}
			    return buyer; 	
			}
			//validate quantity 
			public Boolean validateCart(CartDTO cartDTO, ProductDTO productDTO) throws Exception
			{
				if(cartDTO==null || productDTO==null)
				{
					throw new Exception("Cart or Product is null");
				}
				else {
				if(cartDTO.getQuantity()<=productDTO.getStock())
					return true;
				}
				return false;
			}

		//view past orders
			public List<OrderDetailsDTO> viewPastOrders(Integer buyerid,OrderDetailsDTO[] orders)
			{
				List<OrderDetailsDTO> list = new ArrayList<>();
				
					for(OrderDetailsDTO o : orders)
					{
						if (o.getBuyerid().equals(buyerid))
						{
							list.add(o);
						}
					}
				
				return list;
			}
			
}
