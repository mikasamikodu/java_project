package com.nantian.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.nantian.daoImpl.DataImpl;

public class StudentServer {

	public static void main(String[] args) {
		JAXRSServerFactoryBean bean =new JAXRSServerFactoryBean();
		bean.setServiceBean(new DataImpl());
		bean.setResourceClasses(DataImpl.class);
		bean.setAddress("http://localhost:12345/user");
		bean.create();

	}

}
