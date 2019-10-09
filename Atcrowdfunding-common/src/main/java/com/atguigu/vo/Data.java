package com.atguigu.vo;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.MemberCert;
import com.atguigu.bean.User;

public class Data {
	
	private List<User> datas = new ArrayList<User>();
	private List<Integer> ids = new ArrayList<Integer>();
	private List<MemberCert> certs = new ArrayList<MemberCert>();
	public List<User> getDatas() {
		return datas;
	}
	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<MemberCert> getCerts() {
		return certs;
	}
	public void setCerts(List<MemberCert> certs) {
		this.certs = certs;
	}
}
