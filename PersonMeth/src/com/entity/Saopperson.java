package com.entity;

//import java.util.Date;

public class Saopperson {
	private String sid;
	private String sname;//
	private String sidCard;//
	private String sofficePhone;//
	private String sex;
	private String smobilePhone;//
	private String joinDate;//
	private String svalIdState;//
	private String birthDay;//
	private Integer snubm;
	private String snameid;
	private String smainorgid;
	private String nameCode;
	private String sstate;
	private String ssequence;
	private String arrt1;
	private String arrt2;
	private String arrt3;
	private String arrt4;
	private String arrt5;
	private String arrt6;
	
	
	
	
	public String getSnameid() {
		return snameid;
	}
	public void setSnameid(String snameid) {
		this.snameid = snameid;
	}
	public String getArrt1() {
		return arrt1;
	}
	public void setArrt1(String arrt1) {
		this.arrt1 = arrt1;
	}
	public String getArrt2() {
		return arrt2;
	}
	public void setArrt2(String arrt2) {
		this.arrt2 = arrt2;
	}
	public String getArrt3() {
		return arrt3;
	}
	public void setArrt3(String arrt3) {
		this.arrt3 = arrt3;
	}
	public String getArrt4() {
		return arrt4;
	}
	public void setArrt4(String arrt4) {
		this.arrt4 = arrt4;
	}
	public String getArrt5() {
		return arrt5;
	}
	public void setArrt5(String arrt5) {
		this.arrt5 = arrt5;
	}
	public String getArrt6() {
		return arrt6;
	}
	public void setArrt6(String arrt6) {
		this.arrt6 = arrt6;
	}
	
	
	public String getNameCode() {
		return nameCode;
	}
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	public String getSsequence() {
		return ssequence;
	}
	public void setSsequence(String ssequence) {
		this.ssequence = ssequence;
	}
	public String getSstate() {
		return sstate;
	}
	public void setSstate(String sstate) {
		this.sstate = sstate;
	}
	
	public void setSnubm(Integer snubm) {
		if(snubm==null){
			snubm=99995;
		}else {
			this.snubm = snubm;
		}
	}
	public String getSmainorgid() {
		return smainorgid;
	}
	public void setSmainorgid(String smainorgid) {
		this.smainorgid = smainorgid;
	}
	public Integer getSnubm() {
		return snubm;
	}
	public void setSnubm(String snubm) {
		
		this.snubm =Integer.parseInt(snubm);
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSidCard() {
		return sidCard;
	}
	public void setSidCard(String sidCard) {
		this.sidCard = sidCard;
	}
	public String getSofficePhone() {
		return sofficePhone;
	}
	public void setSofficePhone(String sofficePhone) {
		this.sofficePhone = sofficePhone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSmobilePhone() {
		return smobilePhone;
	}
	public void setSmobilePhone(String smobilePhone) {
		this.smobilePhone = smobilePhone;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getSvalIdState() {
		return svalIdState;
	}
	public void setSvalIdState(String svalIdState) {
		this.svalIdState = svalIdState;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	
}	
