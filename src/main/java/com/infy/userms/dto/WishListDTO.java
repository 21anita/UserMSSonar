package com.infy.userms.dto;

import com.infy.userms.entity.WishList;

public class WishListDTO {
	Integer buyerid;
	Integer prodid;

	public WishListDTO()
	{
		super();
	}
	public WishListDTO(Integer buyerid, Integer prodid)
	{
		this();
		this.buyerid=buyerid;
		this.prodid=prodid;
	}
	public Integer getBuyerId()
	{
	return buyerid;	
	}
	public void setBuyerId(Integer buyerid)
	{
	this.buyerid=buyerid;	
	}
	public Integer getProdId()
	{
	return prodid;	
	}
	public void setProdId(Integer prodid)
	{
	this.prodid=prodid;	
	}

	//convert entity to dto
	public static WishListDTO valueOf(WishList wishList)
	{
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setBuyerId(wishList.getBuyerId());
		wishListDTO.setProdId(wishList.getProdId());
		return wishListDTO;
	}
	//convert DTO into Entity
	public WishList createEntity()
	{
		WishList wishList = new WishList();
		wishList.setBuyerId(this.getBuyerId());
		wishList.setProdId(this.getProdId());
		return wishList;
	}
}
