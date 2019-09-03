package com.it.shop.domain;

import java.util.Date;
import java.util.List;

public class User {
	
	private String id;  		//用户id	
	private int uindex;			//用户注册序列号
	private String uname;		//用户昵称
	private String uaccount;	//用户帐户
	private String upassword;	//用户密码
	private String ugender;		//用户性别
	private String utelephone;	//用户电话
	private String uemail;		//用户电子邮箱
	private Date uregistertime;	//用户注册时间
	private String uimgurl;		//用户头像
	private String utemp1;		//用户信息修改时间
	private String utemp2;		//备用字段2
	private String utemp3;		//备用字段3
	private List<UserAddress> alist;    //用户收货地址
	private List<UserCommodity> clist;  //用户收藏的商品
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUindex() {
		return uindex;
	}
	public void setUindex(int uindex) {
		this.uindex = uindex;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUaccount() {
		return uaccount;
	}
	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUgender() {
		return ugender;
	}
	public void setUgender(String ugender) {
		this.ugender = ugender;
	}
	public String getUtelephone() {
		return utelephone;
	}
	public void setUtelephone(String utelephone) {
		this.utelephone = utelephone;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public Date getUregistertime() {
		return uregistertime;
	}
	public void setUregistertime(Date uregistertime) {
		this.uregistertime = uregistertime;
	}
	public String getUimgurl() {
		return uimgurl;
	}
	public void setUimgurl(String uimgurl) {
		this.uimgurl = uimgurl;
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
	public List<UserAddress> getAlist() {
		return alist;
	}
	public void setAlist(List<UserAddress> alist) {
		this.alist = alist;
	}
	public List<UserCommodity> getClist() {
		return clist;
	}
	public void setClist(List<UserCommodity> clist) {
		this.clist = clist;
	}
}
