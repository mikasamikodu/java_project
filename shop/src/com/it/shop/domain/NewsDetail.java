package com.it.shop.domain;

public class NewsDetail {

	private String id;			//资讯详情id
	private News news;			//资讯
	private String idetail;		//资讯详情
	private String ipicture;	//资讯图片
	private String itemp1;		//备用字段1
	private String itemp2; 		//备用字段2
	private String itemp3;		//备用字段3
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getIdetail() {
		return idetail;
	}
	public void setIdetail(String idetail) {
		this.idetail = idetail;
	}
	public String getIpicture() {
		return ipicture;
	}
	public void setIpicture(String ipicture) {
		this.ipicture = ipicture;
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
}
