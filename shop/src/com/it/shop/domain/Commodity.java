package com.it.shop.domain;

import java.util.List;

public class Commodity {

	private String id;			//商品id
	private int cindex;			//商品注册序列号
	private String cname;		//商品名称
	private String ckind;		//商品类型
	private String ckindid;		//商品类型id
	private String ccategory;	//商品规格型号
	private double cprice;		//商品价格
	private int cstock;			//商品库存
	private String imgurl;		//商品展示图
	private String cdescription;//商品描述
	private String ctemp1;		//商品状态，0表示禁用，1表示正在使用
	private String ctemp2;		//备用字段2
	private String ctemp3;		//备用字段3
	private List<CommodityDetail> clist;  //商品详情项
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCindex() {
		return cindex;
	}
	public void setCindex(int cindex) {
		this.cindex = cindex;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCkind() {
		return ckind;
	}
	public void setCkind(String ckind) {
		this.ckind = ckind;
	}
	public String getCkindid() {
		return ckindid;
	}
	public void setCkindid(String ckindid) {
		this.ckindid = ckindid;
	}
	public String getCcategory() {
		return ccategory;
	}
	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
	}
	public double getCprice() {
		return cprice;
	}
	public void setCprice(double cprice) {
		this.cprice = cprice;
	}
	public int getCstock() {
		return cstock;
	}
	public void setCstock(int cstock) {
		this.cstock = cstock;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getCdescription() {
		return cdescription;
	}
	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
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
	public List<CommodityDetail> getClist() {
		return clist;
	}
	public void setClist(List<CommodityDetail> clist) {
		this.clist = clist;
	}
}
