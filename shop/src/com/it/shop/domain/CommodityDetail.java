package com.it.shop.domain;

public class CommodityDetail {

	private String id;				//商品详情id
	private Commodity commodity;	//商品
	private String cpicture;		//商品各种角度图片
	private String ctemp1;			//备用字段1
	private String ctemp2;			//备用字段2
	private String ctemp3;			//备用字段3
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public String getCpicture() {
		return cpicture;
	}
	public void setCpicture(String cpicture) {
		this.cpicture = cpicture;
	}
	public String getCtemp1() {
		return ctemp1;
	}
	public void setCtemp1(String ctemp1) {
		this.ctemp1 = ctemp1;
	}
	public String getCtemp2() {
		return ctemp2;
	}
	public void setCtemp2(String ctemp2) {
		this.ctemp2 = ctemp2;
	}
	public String getCtemp3() {
		return ctemp3;
	}
	public void setCtemp3(String ctemp3) {
		this.ctemp3 = ctemp3;
	}
}
