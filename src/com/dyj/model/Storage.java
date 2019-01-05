package com.dyj.model;

import java.util.Date;

public class Storage {
  private int id;
  private int goodid;
  private double inprice;
  private Date storagedate;
  private int storagenumber;
  private String storagenote;
  private String goodname;
  
public String getGoodname() {
	return goodname;
}
public void setGoodname(String goodname) {
	this.goodname = goodname;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public int getGoodid() {
	return goodid;
}
public void setGoodid(int goodid) {
	this.goodid = goodid;
}
public double getInprice() {
	return inprice;
}
public void setInprice(double inprice) {
	this.inprice = inprice;
}
public Date getStoragedate() {
	return storagedate;
}
public void setStoragedate(Date storagedate) {
	this.storagedate = storagedate;
}
public int getStoragenumber() {
	return storagenumber;
}
public void setStoragenumber(int storagenumber) {
	this.storagenumber = storagenumber;
}
public String getStoragenote() {
	return storagenote;
}
public void setStoragenote(String storagenote) {
	this.storagenote = storagenote;
}

  
}
