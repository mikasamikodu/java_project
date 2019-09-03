package com.nantian.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.nantian.test2.Data;

public class Test1 {

	public static void main(String[] args) {
		try {
			URL URL = new URL("http://localhost:7777/service?wsdl");
			QName name = new QName("http://data.nantian.com/", "DataImplService");
			
			Service service = Service.create(URL,name);
			Data data = service.getPort(Data.class);
			System.out.println(data.add(33, 22));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
