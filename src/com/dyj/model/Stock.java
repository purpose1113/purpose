package com.dyj.model;

public class Stock {
  private int stockid;
  private int goodid;
  private int goodnumber;
  private double inprice;
  private double saleprice;
  private String stocknote;
  private String goodname;
  private int outstockid;
  private int storageid;
  

public int getOutstockid() {
	return outstockid;
}
public void setOutstockid(int outstockid) {
	this.outstockid = outstockid;
}
public int getStorageid() {
	return storageid;
}
public void setStorageid(int storageid) {
	this.storageid = storageid;
}
public String getGoodname() {
	return goodname;
}
public void setGoodname(String goodname) {
	this.goodname = goodname;
}
public int getStockid() {
	return stockid;
}
public void setStockid(int stockid) {
	this.stockid = stockid;
}
public int getGoodid() {
	return goodid;
}
public void setGoodid(int goodid) {
	this.goodid = goodid;
}
public int getGoodnumber() {
	return goodnumber;
}
public void setGoodnumber(int goodnumber) {
	this.goodnumber = goodnumber;
}

public double getInprice() {
	return inprice;
}
public void setInprice(double inprice) {
	this.inprice = inprice;
}
public double getSaleprice() {
	return saleprice;
}
public void setSaleprice(double saleprice) {
	this.saleprice = saleprice;
}
public String getStocknote() {
	return stocknote;
}
public void setStocknote(String stocknote) {
	this.stocknote = stocknote;
}
  
  
}
