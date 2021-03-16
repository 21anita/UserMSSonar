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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.userms.dto.LoginDTO;
import com.infy.userms.dto.OrderDetailsDTO;
import com.infy.userms.dto.ProductDTO;
import com.infy.userms.dto.SellerDTO;
import com.infy.userms.service.SellerService;

import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class SellerController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	SellerService sellerService;
	@Autowired 
	Environment environment;
	//register seller
		@PostMapping(value = "/seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> createOrder(@RequestBody SellerDTO sellerDTO) throws Exception
		{
			ResponseEntity<String> response =  null;
			logger.info("Adding new Buyer {}", sellerDTO);
			try {
			sellerService.createSeller(sellerDTO);
			response = new ResponseEntity<String>(environment.getProperty("SELLER_REGISTERED"), HttpStatus.OK);
			return response;

			}catch(Exception e)
			{
				ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
				throw exception;
			}
		}

		// Seller Login
		@PostMapping(value = "/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> loginseller(@RequestBody LoginDTO loginDTO)throws Exception {
			logger.info("Login request for buyer with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			boolean status = sellerService.login(loginDTO);
			if(status == true)
			{
				return new ResponseEntity<String>(environment.getProperty("SELLER_LOGIN"), HttpStatus.OK);

			}
			else
				return new ResponseEntity<String>(environment.getProperty("SELLER_LOGIN_FAILED"), HttpStatus.OK);
		}

		//get all sellers
		@GetMapping(value="/sellers",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<SellerDTO> getAllSells()
		{
			logger.info("Fetching all sells");
			return sellerService.getAllSellers();
		}
		//get specific seller
		@GetMapping(value = "/seller/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public SellerDTO getSpecificSell(@PathVariable Integer sellerid) {
			logger.info("Fetching specific seller details {}");
			return sellerService.getSpecificSeller(sellerid);		
		}
		//delete seller 
		@DeleteMapping(value = "/seller/remove/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> deleteSpecificSell(@PathVariable Integer sellerid)throws Exception {
			logger.info("Deleting details of seller {}", sellerid);
			 String productURI = "http://localhost:8300/delete/product/";
			 ResponseEntity<String> response = null;
			 try
			 {
			 boolean status = sellerService.deleteSpecificSeller(sellerid);
			 if(status == true)
			 {
				new RestTemplate().delete(productURI+sellerid,String.class); 
				response = new ResponseEntity<String>(environment.getProperty("SELLER_DELETED"),HttpStatus.OK);
			 }
			 else
			 {
				response = new ResponseEntity<String>(environment.getProperty("SELLER_DOES_NOT_EXISTS"),HttpStatus.BAD_REQUEST);

			 }
		 }catch (Exception e) {
			 throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
		 }
			 return response;
		}
		
			
			//inactive seller account(set isactive=1 to deactivate)
		@PutMapping(value="/deactivate/seller", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void InactiveSellerAcc(@RequestBody SellerDTO sellerDTO)
		{
			logger.info("inactive seller");
			sellerService.InactiveSeller(sellerDTO);
		}
		
		
		//add product (call productms)
		@PostMapping(value = "/add/product", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void addProduct(@RequestBody ProductDTO product)
		{
			//System.out.println(product.getProdid());
			String productURI = "http://localhost:8300/api/product/add";
			new RestTemplate().postForObject(productURI, product,void.class);
			
		}
		
		//delete product
		@GetMapping(value="remove/product/{prodid}")
		public void removeProduct(@PathVariable Integer prodid)
		{
			String product = "http://localhost:8300/delete/";
			new RestTemplate().delete(product+prodid,String.class);
		}
	
		//update stock
		@GetMapping(value="update/stock")
		public void updateStock(@RequestBody ProductDTO product)
		{
			String stockURI="http://localhost:8300/api/stock/";
			new RestTemplate().put(stockURI+product.getProdid(), product);
		}
		
		//view orders placed on their product
		@GetMapping(value="orders/{prodid}")
		public List<OrderDetailsDTO> viewOrdersPlacedOnProduct(@PathVariable Integer prodid)
		{
			String orderURI="http://localhost:8100/api/allorders";
			//OrderDetailsDTO orders = new OrderDetailsDTO();
			OrderDetailsDTO[] orders = new RestTemplate().getForObject(orderURI,OrderDetailsDTO[].class);
			List<OrderDetailsDTO> ordersPlaced=sellerService.viewPlacedOrdersOnProduct(orders, prodid);
			return ordersPlaced;
		}
		//accept orders
		@GetMapping(value="orders/accept")
		public String acceptOrder(@RequestBody OrderDetailsDTO orderDetailsDTO)
		{
			String orderURI = "http://localhost:8100/api/placeOrder";
			String str = new RestTemplate().postForObject(orderURI, orderDetailsDTO, String.class);
			return str;
		}
		//delete orders
		@DeleteMapping(value="order/delete/{orderid}")
		public void deleteOrder(@PathVariable Integer orderid)
		{
			String orderURI="http://localhost:8100/api/order/";
			new RestTemplate().delete(orderURI+orderid,String.class);
			
		}
		//update order status
		@PutMapping(value="order/statusupdate")
		public void updateStatus(@RequestBody OrderDetailsDTO order)
		{
			String orderURI="http://localhost:8100/api/change/status";
			new RestTemplate().put(orderURI,order,String.class);
		}
		//reorder
		@GetMapping(value="/reorder/{orderid}/{prodid}")
		public String reOrder(@PathVariable Integer orderid, @PathVariable Integer prodid)
		{
			String orderURI="http://localhost:8100/api/reorder/";
			String str = new RestTemplate().getForObject(orderURI+orderid+"/"+prodid,String.class);
			return str;
		}
		
}
