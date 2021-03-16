package com.infy.userms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"EMAIL", "PHONENUMBER"}), name="buyer")

public class Buyer {
@Id
//@GeneratedValue
@Column(nullable = false,length=11)
Integer buyerid;
@Column(nullable = false, length=45)
String name;
@Column(nullable = false, length=45)
String email;
@Column(nullable = false, length=45,unique=true)
String phonenumber;
@Column(nullable = false, length=45,unique=true)
String password;
@Column(length=1)
Integer isprivileged;
@Column(length=11)
Integer rewardpoints;
@Column(length=1)
Integer isactive;
public Buyer()
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
}