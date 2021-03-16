package com.infy.userms.dto;

public class ProductsOrderedDTO {
	Integer orderid;
	Integer prodid;
	Integer sellerid;
	Integer quantity;
	String status;
	Double price;
	
	public ProductsOrderedDTO()
	{
		super();
	}
	public ProductsOrderedDTO(Integer orderid, Integer prodid, Integer sellerid, Integer quantity, String status, Double price)
	{
		this();
		this.orderid=orderid;
		this.prodid=prodid;
		this.sellerid=sellerid;
		this.quantity=quantity;
		this.status=status;
		this.price=price;
	}
	public Integer getOrderId() {
		return orderid;
	}
	public void setOrderId(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getProdId() {
		return prodid;
	}
	public void setProdId(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getSellerId() {
		return sellerid;
	}
	public void setSellerId(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
/*
	//Entity into DTO
	public static ProductsOrderedDTO valueOf(ProductsOrdered productsOrdered)
	{
		ProductsOrderedDTO productsOrderedDTO = new ProductsOrderedDTO();
		productsOrderedDTO.setOrderId(productsOrdered.getOrderId());
		productsOrderedDTO.setPrice(productsOrdered.getPrice());
		productsOrderedDTO.setProdId(productsOrdered.getProdId());
		productsOrderedDTO.setQuantity(productsOrdered.getQuantity());
		productsOrderedDTO.setSellerId(productsOrdered.getSellerId());
		productsOrderedDTO.setStatus(productsOrdered.getStatus());
		return productsOrderedDTO;
	}
	
	//DTO into Entity
	public ProductsOrdered createEntity()
	{
		ProductsOrdered p = new ProductsOrdered();
		p.setOrderId(this.getOrderId());
		p.setPrice(this.getPrice());
		p.setProdId(this.getProdId());
		p.setQuantity(this.getQuantity());
		p.setSellerId(this.getSellerId());
		p.setStatus(this.getStatus());
		return p;
	}*/
	
	@Override
	public String toString() {
		return "ProductsOrderedDTO [OrderId = " + orderid + ", price = " + price + ", prodId = " + prodid + ", quantity = " + quantity + ", sellerId = " + sellerid + ", status = " + status; 
	}
}
