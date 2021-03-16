package com.infy.userms.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.userms.dto.BuyerDTO;
import com.infy.userms.dto.CartDTO;
import com.infy.userms.dto.LoginDTO;
import com.infy.userms.dto.OrderDetailsDTO;
import com.infy.userms.dto.ProductDTO;
import com.infy.userms.entity.Buyer;
import com.infy.userms.service.BuyerService;

import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class BuyerController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	BuyerService buyerService;
	@Autowired 
	Environment environment;
	
	//register buyer
		@PostMapping(value = "/buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> addBuyer(@RequestBody Buyer buyer) throws Exception
		{
			ResponseEntity<String> response =  null;
			logger.info("Adding new Buyer {}", buyer);
			try {
			buyerService.createBuyer(buyer);
			response = new ResponseEntity<String>(environment.getProperty("BUYER_REGISTERED"), HttpStatus.OK);
			return response;
			}catch(Exception e)
			{
				ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
				throw exception;
		
			}
		
		}
		
		// Buyer Login
		@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws Exception {
			logger.info("Login request for buyer with password {}");
			 ResponseEntity<String> response = null;
			try
			{
			boolean status = buyerService.login(loginDTO);
			if(status == true)
			{
				response = new ResponseEntity<String>(environment.getProperty("BUYER_LOGIN_SUCCESSFUL"), HttpStatus.OK);

			}
			else
			{
				response = new ResponseEntity<String>(environment.getProperty("LOGIN_FAILED"), HttpStatus.BAD_REQUEST);
			}
			}catch (Exception e) {
				 throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
			 }
			 return response;
			}
		

		//get all buyers
		@GetMapping(value="/buyers",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<BuyerDTO> getAllBuys()throws Exception
		{
			List<BuyerDTO> response = new ArrayList<>();
			logger.info("Fetching all sells");
			response =  buyerService.getAllBuyers();
			if(response.isEmpty())
			{
				throw new Exception(environment.getProperty("NO_DATA"));
			}
			
			return response;
		}
		
		//get specific buyer
		@GetMapping(value = "/buyer/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public BuyerDTO getSpecificBuy(@PathVariable Integer buyerid) {
			logger.info("Fetching specific buyer details {}");
			return buyerService.getSpecificBuyer(buyerid);		
		}
		//delete buyer 
		@DeleteMapping(value = "/buyer/remove/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> deleteSpecificBuy(@PathVariable Integer buyerid) throws Exception {
			logger.info("Deleting details of buyer {}", buyerid);
			 ResponseEntity<String> response = null;
			 try
			 {
				 boolean status = buyerService.deleteSpecificBuyer(buyerid);
				 if(status == true)
				 {
					response = new ResponseEntity<String>(environment.getProperty("BUYER_DELETED"),HttpStatus.OK);
				 }
				 else
				 {
					response = new ResponseEntity<String>(environment.getProperty("BUYER_DOES_NOT_EXISTS"),HttpStatus.BAD_REQUEST);

				 }
			 }catch (Exception e) {
				 throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
			 }
			
			return response;
		}
		
		//validate quantity 
		@GetMapping(value="/validatecart/{buyerid}/{prodid}",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> ValidateCart(@PathVariable Integer buyerid, @PathVariable Integer prodid)throws Exception
		{
			ResponseEntity<String> response = null;
			try
			{
			String cart = "http://localhost:8200/api/cart/";
			String stock = "http://localhost:8300/api/products/";
			CartDTO carts = new RestTemplate().getForObject(cart+buyerid+"/"+prodid,CartDTO.class);
			ProductDTO product = new RestTemplate().getForObject(stock+prodid, ProductDTO.class);
			boolean b = buyerService.validateCart(carts, product);
			System.out.println(b);
			if(b==true)
			{
				response = new ResponseEntity<String>(environment.getProperty("QUANTITY_AVAILABLE"),HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<String>(environment.getProperty("QUANTITY_NOT_AVAILABLE"),HttpStatus.OK);
			}
			}catch (Exception e) {
				 throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
			 }
			
			return response;

			
		}
		//view past orders
		@GetMapping(value="/pastorders/{buyerid}")
		public List<OrderDetailsDTO> ViewOrders(@PathVariable Integer buyerid)
		{
			List<OrderDetailsDTO> past = new ArrayList<>();
			
			String order = "http://localhost:8100/api/allorders";
			OrderDetailsDTO[] orders = new RestTemplate().getForObject(order, OrderDetailsDTO[].class);
			past = buyerService.viewPastOrders(buyerid,orders);
			return past;
	
		}
		
		

				//inactive buyer account(isactive=1 to deactivate account)
				@PutMapping(value="/deactivate/buyer", consumes = MediaType.APPLICATION_JSON_VALUE)
				public void InactiveByurAcc(@RequestBody BuyerDTO buyerDTO)
				{
					logger.info("inactive buyer");
					buyerService.InactiveBuyer(buyerDTO);
				}
		
		
		
		
}
