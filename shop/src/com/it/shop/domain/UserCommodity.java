package com.it.shop.domain;

public class UserCommodity {

	private String id;				//收藏商品信息id
	private User user;				//用户
	private Commodity commodity;	//商品
	private String utemp1;			//信息状态，0表示禁用，1表示可用	
	private String utemp2;			//备用字段2
	private String utemp3;			//备用字段3
	
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
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public String getUtemp1() {
		return utemp1;
	}
	public void setUtemp1(String utemp1) {
		this.utemp1 = utemp1;
	}
	public String getUtemp2() {
		return utemp2;
	}
	public void setUtemp2(String utemp2) {
		this.utemp2 = utemp2;
	}
	public String getUtemp3() {
		return utemp3;
	}
	public void setUtemp3(String utemp3) {
		this.utemp3 = utemp3;
	}
}
