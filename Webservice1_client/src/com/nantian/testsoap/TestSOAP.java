package com.nantian.testsoap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

import com.nantian.client.MyService;
import com.nantian.client.MyServiceImplService;
import com.nantian.client.User;
//import com.nantian.client.UserException_Exception;

public class TestSOAP {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:9999/server");
			QName qname = new QName("http://soap2.nantian.com/","MyServiceImplService");
			MyService service = new MyServiceImplService(url,qname).getMyServiceImplPort();
//			service.login("张三", "1234");
//			service.login("zhangsan", "1234");
			User user = new User();
			user.setId("6");
			user.setUsername("李四");
			user.setNickname("tom");
			user.setPassword("1234");
			Holder<User> hold = new Holder<User>(user);
			service.addUser(hold);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
//		} 
//		catch (UserException_Exception e) {
//			System.out.println(e.getMessage());
		} catch(SOAPFaultException e) {
			System.out.println(e.getMessage());
		}
	}

}
