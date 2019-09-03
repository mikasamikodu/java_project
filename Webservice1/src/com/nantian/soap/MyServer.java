package com.nantian.soap;

import javax.xml.ws.Endpoint;

public class MyServer {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9090/server", new MyServiceImpl());

	}

}
