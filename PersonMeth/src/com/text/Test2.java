package com.text;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.service.impl.ImportVendorServiceImpl;
import com.service.ws.ImportVendor;
import com.service.ws.importCorp;
import com.service.ws.importName;
import com.service.ws.impl.importCorpImpl;

public class Test2 {
	public static void main(String[] args) {
		importcorpInfo();//测试组织机构
//		importNameInfo();//测试人员信息
		//importVendorInfo();//测试供应商
	}

	private static void importcorpInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		importCorp service = new importCorpImpl();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<sscinterface systemname='zsj' billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='报文唯一标识'>");
		sb.append("<bill>");//发票
		sb.append("<head>");//头部
		sb.append("<ORGANIZATIONID></ORGANIZATIONID>");//organization id组织ID
		sb.append("<COMPANYID>20000002</COMPANYID>");//公司ID
		sb.append("<DEPARTMENTID>100006</DEPARTMENTID>");//部门ID
		sb.append("<SORGKINDID>dpt</SORGKINDID>");//s org kind id 组织类型ID
		sb.append("</head>");
		sb.append("<body>");//主体
		sb.append("<data>");// 人员信息
		
		sb.append("<ORGANIZATION></ORGANIZATION>");//Organization 组织
		sb.append("<COMPANY>国家开发投资公司</COMPANY>");//公司
		sb.append("<DEPARTMENT>北京一部</DEPARTMENT>");//部门
//		sb.append("<SVALIDSTATE>劳务派遣</SVALIDSTATE>"); //s valid state 人员状态
		sb.append("<SVALIDSTATE>启用</SVALIDSTATE>"); //s valid state 人员状态
		sb.append("<SUPERIORDEPARTMENT></SUPERIORDEPARTMENT>");//s uperiordepartment
		sb.append("<SUPERIORDEPARTMENTID></SUPERIORDEPARTMENTID>");//s uperiordepartment id
		sb.append("</data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importCorpInfo(sb.toString()));
	}

	private static void importNameInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		importName service = (importName) ctx.getBean("clientName");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append(
				"<sscinterface systemname='zsj'  billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='报文唯一标识'>");
		sb.append("<bill>");
		sb.append("<head>");
		// 组织机构
		sb.append("<NAME>邢文欣</NAME>");
		sb.append("<ORGANIZATIONID>20000365</ORGANIZATIONID>");
		sb.append("<DEPARTMENTID>20002066</DEPARTMENTID>");
		sb.append("<IDCARD>110105198209269473</IDCARD>");
		sb.append("<SSTATE>1</SSTATE>");
		sb.append("<COMPANYID></COMPANYID>");

		sb.append("</head>");
		sb.append("<body>");
		sb.append("<data>");
		// 人员信息
		sb.append("<SID>10020502</SID>");
		sb.append("<SNAMEID>xingwenxin9473</SNAMEID>");
		sb.append("<ORGANIZATION>国投物业北京一分公司</ORGANIZATION>");
		sb.append("<DEPARTMENT>外派</DEPARTMENT>");
		sb.append("<SORGKINDID>psm</SORGKINDID>");
		sb.append("<SSEQUENCE>psm</SSEQUENCE>");
		sb.append("<SEX>男</SEX>");
		sb.append("<BIRTHDAY></BIRTHDAY>");// 出生日期
		sb.append("<JOINDATE>2017-05-31</JOINDATE>");// 入职日期
		sb.append("<SOFFICEPHONE>010-63288355</SOFFICEPHONE>");// 手机号
		sb.append("<SMOBILEPHONE>13910068633</SMOBILEPHONE>");// 办公好
		sb.append("<SVALIDSTATE>在岗在编</SVALIDSTATE>"); // 人员状态
		sb.append("<Attr1>N</Attr1><Attr2></Attr2><Attr3></Attr3><Attr4></Attr4><Attr5></Attr5><Attr6></Attr6></data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importNameInfo(sb.toString()));
	}

	@SuppressWarnings("unused")
	private static void importVendorInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		ImportVendor service = (ImportVendor) ctx.getBean("clientVendor");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append(
				"<sscinterface systemname='zsj'  billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='报文唯一标识'>");
		sb.append("<bill>");
		sb.append("<head>");
		// 组织机构
		sb.append("<VENDOR_ID>13254412</VENDOR_ID>");
		sb.append("<VENDOR_SITE_ID>400120</VENDOR_SITE_ID>");
		sb.append("<ORG_ID>20000002</ORG_ID>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<data>");
		// 人员信息
		sb.append("<VENDOR_NAME>112321</VENDOR_NAME>");
		sb.append("<VENDOR_SITE_CODE>北京</VENDOR_SITE_CODE>");
		sb.append("<ORG_NAME>国家开发投资集团股份公司</ORG_NAME>");
		sb.append("<Attr1></Attr1><Attr2></Attr2><Attr3></Attr3><Attr4></Attr4><Attr5></Attr5><Attr6></Attr6></data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importVendorInfo(sb.toString()));
	}

}
