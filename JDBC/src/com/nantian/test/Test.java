package com.nantian.test;

import com.service.Data;
import com.service.DataImplService;
//import com.service.Student;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Data data = new DataImplService().getDataImplPort();
//		String select2 = data.select2("韩东军");
//		System.out.println(select2);
		StringBuilder soap=new StringBuilder(); //构造请求报文
		soap.append(" <soapenv:Envelope  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
		soap.append(" xmlns:wor=\"http://www.horizon.com/workflow/webservice/client/workflowCommon\">");
		soap.append(" <soapenv:Header>");
		soap.append(" <HZWFService  xmlns=\"http://www.huizhengtech.com/webservice/workflow\"");
		soap.append(" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"");
		soap.append(" SOAP-ENV:actor=\"http://www.w3.org/2003/05/soap-envelope/role/next\">admin&admin</HZWFService>");
		soap.append(" </soapenv:Header>");
		soap.append(" <soapenv:Body>");
		soap.append(" <wor:sysLogin>");
		soap.append(" <loginName>loginname</loginName >");
		soap.append(" <password>password</password>");
		soap.append(" <dbidentifier>system</dbidentifier>");
		soap.append(" </wor:sysLogin>");
		soap.append(" </soapenv:Body>");
		soap.append(" </soapenv:Envelope>");
		String requestSoap=soap.toString();
	}

}
