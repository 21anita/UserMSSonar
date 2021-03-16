package com.infy.userms.dto;

import com.infy.userms.entity.Seller;

public class SellerDTO {
	int sellerid;
	String name;
	String email;
	String phonenumber;
	String password;
	int isactive;
	public SellerDTO() {
		super();
	}
	public SellerDTO(int sellerid, String name, String email, String phonenumber, String password, int isactive)
	{
		this();
		this.sellerid=sellerid;
		this.name=name;
		this.email=email;
		this.phonenumber=phonenumber;
		this.password=password;
		this.isactive=isactive;
	}
	public int getSellerId()
	{
		return sellerid;
	}
	public void setSellerId(int sellerid)
	{
	this.sellerid=sellerid;	
	}
	public String getName()
	{
	return name;	
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getEmail()
	{
	return email;
	}
	public void setEmail(String email)
	{
	this.email=email;
	}
	public String getPhonenumber()
	{
	return phonenumber;
	}
	public void setPhonenumber(String phonenumber)
	{
	this.phonenumber=phonenumber;
	}
	public String getPassword()
	{
	return password;	
	}
	public void setPassword(String password)
	{
	this.password=password;
	}
	public int getIsActive()
	{
	return isactive;	
	}
	public void setIsActive(int isactive)
	{
	this.isactive=isactive;	
	}
//Convert entity into DTO
	public static SellerDTO valueOf(Seller seller)
	{
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setIsActive(seller.getIsActive());
		sellerDTO.setName(seller.getName());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setPhonenumber(seller.getPhonenumber());
		sellerDTO.setSellerId(seller.getSellerId());
		return sellerDTO;
	}
	//convert DTO into Entity
	public Seller createEntity()
	{
		Seller seller = new Seller();
		seller.setEmail(this.getEmail());
		seller.setIsActive(this.getIsActive());
		seller.setName(this.getName());
		seller.setPhonenumber(this.getPhonenumber());
		seller.setPassword(this.getPassword());
		seller.setSellerId(this.getSellerId());
		return seller;
	}
	
}
