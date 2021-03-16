package com.infy.userms.dto;

import com.infy.userms.entity.Cart;

public class CartDTO {
	Integer buyerid;
	Integer prodid;
	Integer quantity;

	public CartDTO()
	{
		super();
	}
	public CartDTO(Integer buyerid, Integer prodid, Integer quantity)
	{
		this();
		this.buyerid=buyerid;
		this.prodid=prodid;
		this.quantity=quantity;
	}
	public void setBuyerId(Integer buyerid)
	{
	this.buyerid=buyerid;	
	}
	public Integer getBuyerId()
	{
	return buyerid;	
	}
	public void setProdId(Integer prodid)
	{
	this.prodid=prodid;	
	}
	public Integer getProdId()
	{
	return prodid;
	}
	public void setQuantity(Integer quantity)
	{
	 this.quantity=quantity;	
	}
	public Integer getQuantity()
	{
	return quantity;	
	}
	
	//convert entity to dto
	public static CartDTO valueOf(Cart cart)
	{
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerId(cart.getBuyerId());
		cartDTO.setProdId(cart.getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}
	//convert DTO to entity
	public Cart createEntity()
	{
		Cart cart = new Cart();
		cart.setBuyerId(this.getBuyerId());
		cart.setProdId(this.getProdId());
		cart.setQuantity(this.getQuantity());
		return cart;
	}
}
