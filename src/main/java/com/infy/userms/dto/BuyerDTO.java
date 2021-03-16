package com.infy.userms.dto;

import com.infy.userms.entity.Buyer;

public class BuyerDTO {
	Integer buyerid;
	String name;
	String email;
	String phonenumber;
	String password;
	Integer isprivileged;
	Integer rewardpoints;
	Integer isactive;
	
	public BuyerDTO()
	{
		super();
	}
	
	public BuyerDTO(Integer buyerid, String name, String email, String phonenumber, String password, Integer isprivileged, Integer rewardpoints, Integer isactive)
	{
		this();
		this.buyerid=buyerid;
		this.name=name;
		this.email=email;
		this.phonenumber=phonenumber;
		this.password=password;
		this.isprivileged=isprivileged;
		this.rewardpoints=rewardpoints;
		this.isactive=isactive;
	}
	
	public Integer getBuyerId()
	{
		return buyerid;
	}
	public void setBuyerId(Integer buyerid)
	{
		this.buyerid=buyerid;
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
	public Integer getIsPrivileged()
	{
		return isprivileged;
	}
	public void setIsPrivileged(Integer isprivileged)
	{
		this.isprivileged=isprivileged;
	}
	public Integer getRewardPoints()
	{
		return rewardpoints;
	}
	public void setRewardPoints(Integer rewardpoints)
	{
		this.rewardpoints=rewardpoints;
	}
	public Integer getIsActive()
	{
		return isactive;
	}
	public void setIsActive(Integer isactive)
	{
		this.isactive=isactive;
	}
	
	// Converts Entity into DTO
	public static BuyerDTO valueOf(Buyer buyer)
	{
	BuyerDTO buyerDTO = new BuyerDTO();	
	buyerDTO.setBuyerId(buyer.getBuyerId());
	buyerDTO.setEmail(buyer.getEmail());
	buyerDTO.setIsActive(buyer.getIsActive());
	buyerDTO.setIsPrivileged(buyer.getIsActive());
	buyerDTO.setName(buyer.getName());
	buyerDTO.setPassword(buyer.getPassword());
	buyerDTO.setPhonenumber(buyer.getPhonenumber());
	buyerDTO.setRewardPoints(buyer.getRewardPoints());
	return buyerDTO;
	}
	
	//converts DTO into Entity
	public  Buyer createEntity()
	{
	Buyer buyer = new Buyer();
	buyer.setBuyerId(this.getBuyerId());
	buyer.setEmail(this.getEmail());
	buyer.setIsActive(this.getIsActive());
	buyer.setIsPrivileged(this.getIsPrivileged());
	buyer.setName(this.getName());
	buyer.setPassword(this.getPassword());
	buyer.setPhonenumber(this.getPhonenumber());
	buyer.setRewardPoints(this.getRewardPoints());
	return buyer;
	}
}
