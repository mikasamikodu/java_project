package com.it.shop.domain;

import java.util.Date;

public class CartDetail {

	private String id;
	private Cart cart;
	private Commodity commodity;
	private Date saddtime;
	private String sstate;
	private String stemp1;
	private String stemp2;
	private String stemp3;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Date getSaddtime() {
		return saddtime;
	}
	public void setSaddtime(Date saddtime) {
		this.saddtime = saddtime;
	}
	public String getSstate() {
		return sstate;
	}
	public void setSstate(String sstate) {
		this.sstate = sstate;
	}
	public String getStemp1() {
		return stemp1;
	}
	public void setStemp1(String stemp1) {
		this.stemp1 = stemp1;
	}
	public String getStemp2() {
		return stemp2;
	}
	public void setStemp2(String stemp2) {
		this.stemp2 = stemp2;
	}
	public String getStemp3() {
		return stemp3;
	}
	public void setStemp3(String stemp3) {
		this.stemp3 = stemp3;
	}
}
