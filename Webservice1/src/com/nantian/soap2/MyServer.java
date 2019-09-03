package com.nantian.soap2;

import javax.xml.ws.Endpoint;

public class MyServer {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/server", new MyServiceImpl());

	}

}
