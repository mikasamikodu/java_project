package com.it.shop.domain;

import java.util.List;

public class Cart {

	private String id;				//购物车id
	private User user;				//购物车的用户
	private String stemp1;			//备用字段1
	private String stemp2;			//备用字段2
	private String stemp3;			//备用字段3
	private List<CartDetail> clist; //购物车内的商品
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public List<CartDetail> getClist() {
		return clist;
	}
	public void setClist(List<CartDetail> clist) {
		this.clist = clist;
	}
}
