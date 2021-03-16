package com.infy.userms.validator;

import com.infy.userms.entity.Buyer;
import com.infy.userms.entity.Seller;

public class Validator {

	public static void validateBuyer(Buyer buyer) throws Exception
	{
		if(!validateBuyerName(buyer.getName())) 
		{
			System.out.println("Invalid buyer name");
			throw new Exception("Validator.INVALID_BUYER_NAME");
		}
		if(!validateBuyerEmailId(buyer.getEmail())) 
		{
			System.out.println("Invalid buyer email");
			throw new Exception("Validator.INVALID_BUYER_EMAIL");
		}
		if(!validateBuyerPassword(buyer.getPassword())) 
		{
			System.out.println("Invalid buyer password");
			throw new Exception("Validator.INVALID_BUYER_PASSWORD");
		} 
		if(!validateBuyerPhoneNumber(buyer.getPhonenumber())) 
		{
			System.out.println("Invalid buyer phone number");
			throw new Exception("Validator.INVALID_BUYER_PHONENUMBER");
		}	
	}
	
	public static void validateSeller(Seller seller) throws Exception
	{

		if(!validateSellerName(seller.getName())) 
		{
			System.out.println("Invalid seller name");
			throw new Exception("Validator.INVALID_SELLER_NAME");
		}
		if(!validateSellerEmailId(seller.getEmail())) 
		{
			System.out.println("Invalid seller email");
			throw new Exception("Validator.INVALID_SELLER_EMAIL");
		}
		if(!validateSellerPassword(seller.getPassword())) 
		{
			System.out.println("Invalid seller password");
			throw new Exception("Validator.INVALID_SELLER_PASSWORD");
		}
		if(!validateSellerPhoneNumber(seller.getPhonenumber())) 
		{
			System.out.println("Invalid seller phone number");
			throw new Exception("Validator.INVALID_SELLER_PHONENUMBER");
		}
	}

	public static Boolean validateBuyerName(String name)
	{
		boolean a = false;
		String regex1="[a-zA-Z][a-zA-Z ]*";
		if(name.matches(regex1))
		{
			a = true;
		}
		else
		{
			a = false;
		}
		return a;
	}
	
	public static Boolean validateBuyerEmailId(String email)
	{
		String regex="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		if(email.matches(regex))
			return true;
		return false;
	}
	public static Boolean validateBuyerPassword(String password)
	{
		String regex="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{7,20}";
		if(password.matches(regex))
			return true;
		else
			return false;
	}
	public static Boolean validateBuyerPhoneNumber(String phonenumber)
	{
		String regex="^[0-9]{10}";
		if(phonenumber.matches(regex))
			return true;
		return false;
		
	}
	
	public static Boolean validateSellerName(String name)
	{
		String regex1="[a-zA-Z][a-zA-Z ]*";
		if(name.matches(regex1))
		{
			return true;
		}
		return false;
	}
	
	public static Boolean validateSellerEmailId(String email)
	{
		String regex="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		if(email.matches(regex))
			return true;
		return false;
	}
	public static Boolean validateSellerPassword(String password)
	{
		String regex="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{7,20}";
		if(password.matches(regex))
			return true;
		else
			return false;
	}
	public static Boolean validateSellerPhoneNumber(String phonenumber)
	{
		String regex="^[0-9]{10}";
		if(phonenumber.matches(regex))
			return true;
		return false;
		
	}
}
