package org.example.mywsdl;

import javax.xml.ws.Endpoint;

public class MyServer {

	public static void main(String[] args) {
//		Endpoint.publish("http://erptest2.sdic.com.cn:9999/GtjtIntfService/service/WsExampleService",new MyServiceImpl());
		Endpoint.publish("http://localhost:9999/service",new MyServiceImpl());

	}

}
