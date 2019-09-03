package com.it.shop.domain;

import java.util.Date;
import java.util.List;

public class News {

	private String id;			//资讯id
	private int iindex;			//资讯注册序列号
	private String ititle;		//资讯标题
	private String iimgurl;		//资讯图片
	private String isummary;	//资讯梗概
	private Date itime;			//资讯发布时间
	private int ipraise;		//资讯点赞数量
	private String itemp1;		//备用字段1
	private String itemp2;		//备用字段2
	private String itemp3;		//备用字段3
	private List<NewsDetail> nlist;  //资讯详情项
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIindex() {
		return iindex;
	}
	public void setIindex(int iindex) {
		this.iindex = iindex;
	}
	public String getItitle() {
		return ititle;
	}
	public void setItitle(String ititle) {
		this.ititle = ititle;
	}
	public String getIimgurl() {
		return iimgurl;
	}
	public void setIimgurl(String iimgurl) {
		this.iimgurl = iimgurl;
	}
	public String getIsummary() {
		return isummary;
	}
	public void setIsummary(String isummary) {
		this.isummary = isummary;
	}
	public Date getItime() {
		return itime;
	}
	public void setItime(Date itime) {
		this.itime = itime;
	}
	public int getIpraise() {
		return ipraise;
	}
	public void setIpraise(int ipraise) {
		this.ipraise = ipraise;
	}
	public String getItemp1() {
		return itemp1;
	}
	public void setItemp1(String itemp1) {
		this.itemp1 = itemp1;
	}
	public String getItemp2() {
		return itemp2;
	}
	public void setItemp2(String itemp2) {
		this.itemp2 = itemp2;
	}
	public String getItemp3() {
		return itemp3;
	}
	public void setItemp3(String itemp3) {
		this.itemp3 = itemp3;
	}
	public List<NewsDetail> getNlist() {
		return nlist;
	}
	public void setNlist(List<NewsDetail> nlist) {
		this.nlist = nlist;
	}
}
