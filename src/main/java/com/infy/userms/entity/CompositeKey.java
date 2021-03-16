package com.infy.userms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {
    private static final long serialVersionUID = 1L;

	@Column(name="buyerid")
	Integer buyerid;
	@Column(name = "prodid")
	Integer prodid;
	/*public CompositeKey(Integer orderid,Integer prodid)
	{
		this.orderid=orderid;
		this.prodid=prodid;
	}*/
	public Integer getBuyerId() {
		return buyerid;
	}
	public void setBuyerId(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getProdId() {
		return prodid;
	}
	public void setProdId(Integer prodid) {
		this.prodid = prodid;
	}
}
