package com.it.shop.domain;

import java.util.Date;
import java.util.List;

public class Order {

	private String id;			//订单id
	private String ono;			//订单号
	private User user;			//用户
	private double omoney;		//订单金额	
	private String opaystate;	//订单支付状态
	private Date oordertime;	//订单添加时间
	private String otemp1;		//订单有效性，0表示无效，1表示有效
	private String otemp2;		//备用字段2
	private String otemp3;		//备用字段3
	private List<OrderDetail> olist;  //用户订单项
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getOmoney() {
		return omoney;
	}
	public void setOmoney(double omoney) {
		this.omoney = omoney;
	}
	public String getOpaystate() {
		return opaystate;
	}
	public void setOpaystate(String opaystate) {
		this.opaystate = opaystate;
	}
	public Date getOordertime() {
		return oordertime;
	}
	public void setOordertime(Date oordertime) {
		this.oordertime = oordertime;
	}
	public String getOtemp1() {
		return otemp1;
	}
	public void setOtemp1(String otemp1) {
		this.otemp1 = otemp1;
	}
	public String getOtemp2() {
		return otemp2;
	}
	public void setOtemp2(String otemp2) {
		this.otemp2 = otemp2;
	}
	public String getOtemp3() {
		return otemp3;
	}
	public void setOtemp3(String otemp3) {
		this.otemp3 = otemp3;
	}
	public List<OrderDetail> getOlist() {
		return olist;
	}
	public void setOlist(List<OrderDetail> olist) {
		this.olist = olist;
	}
}
