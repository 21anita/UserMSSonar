package com.infy.userms.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
@Id
@Column(nullable = false,length=11)
Integer sellerid;
@Column(nullable = false,length=45)
String name;
@Column(nullable = false,length=45)
String email;
@Column(nullable = false,length=45)
String phonenumber;
@Column(nullable = false,length=45)
String password;
@Column
Integer isactive;
public Seller()
{
super();	
}

public Integer getSellerId()
{
	return sellerid;
}
public void setSellerId(Integer sellerid)
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
}
