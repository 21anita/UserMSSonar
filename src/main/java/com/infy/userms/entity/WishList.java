package com.infy.userms.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="wishlist")
@IdClass(CompositeKey.class)

public class WishList implements Serializable{
private static final long serialVersionUID = 1L;
@Id
@Column(nullable = false,length=11)
Integer buyerid;
@Id
@Column(nullable = false,length=11)
Integer prodid;

public WishList()
{
super();	
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
}
