package com.infy.userms.dto;

import java.sql.Date;
import java.util.List;


public class OrderDetailsDTO {
	Integer orderid;
	Integer buyerid;
	float amount;
	Date date;
	String address;
	String status;
	List<ProductsOrderedDTO> productsOrdered;
	public OrderDetailsDTO() {
		super();
		
	}
	public OrderDetailsDTO(Integer orderid, Integer buyerid, float amount, Date date, String address, String status) {
		super();
		this.orderid = orderid;
		this.buyerid = buyerid;
		this.amount = amount;
		this.date = date;
		this.address = address;
		this.status = status;
	}
	public List<ProductsOrderedDTO> getProductsOrdered()
	{
		return productsOrdered;
	}
	public void setProductsOrdered(List<ProductsOrderedDTO> productsOrdered)
	{
		this.productsOrdered=productsOrdered;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}