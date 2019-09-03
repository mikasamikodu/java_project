package com.service.ws.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.service.EmpDeptService;
import com.service.impl.EmpDeptServiceImpl;
import com.service.ws.importName;
import com.util.ImportPersonInfo;

//import sun.util.logging.resources.logging;

@WebService(endpointInterface="com.service.ws.importName",serviceName="/importName")
public class importNameImpl implements importName {
	 protected Logger log = ImportPersonInfo.log;
	 
	EmpDeptService empdeptservice=new EmpDeptServiceImpl();

	//@Override
	public String importNameInfo(String requestXml) {
		log.info("||||||||||");
		log.info("||||||||||");
		log.info("||||||||||");
		log.info("======开始接收主数据人员信息======");
		
		String strInfo=empdeptservice.importName(requestXml);
		log.info("======接收结束=======");
		log.info("======反馈给主数据报文:"+strInfo+"========");
		return strInfo;
	}
	 
	public EmpDeptService getEmpdeptservice() {
		return empdeptservice;
	}
	public void setEmpdeptservice(EmpDeptService empdeptservice) {
		this.empdeptservice = empdeptservice;
	}	
	
}
