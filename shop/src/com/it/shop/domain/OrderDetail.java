package com.it.shop.domain;

public class OrderDetail {

	private String id;				//订单详情id
	private Order order;			//订单
	private Commodity commodity;	//商品
	private int onumber;			//购买商品数量
	private String otemp1;			//备用字段1
	private String otemp2;			//备用字段2
	private String otemp3;			//备用字段3
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public int getOnumber() {
		return onumber;
	}
	public void setOnumber(int onumber) {
		this.onumber = onumber;
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
}
