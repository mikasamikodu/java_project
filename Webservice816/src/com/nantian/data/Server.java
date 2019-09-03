package com.nantian.data;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		String url = "http://localhost:9090/service";
		Data data = new DataImpl();
		Endpoint.publish(url, data);
		System.out.println("publish success!!!");
	}

}
