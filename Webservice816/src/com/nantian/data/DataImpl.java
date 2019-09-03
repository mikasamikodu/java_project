package com.nantian.data;

import javax.jws.WebService;

@WebService(endpointInterface="com.nantian.data.Data")
public class DataImpl implements Data {

	@Override
	public int add(int a, int b) {
		System.out.println(a+"+"+b+"+"+(a+b));
		return a+b;
	}

	@Override
	public int min(int a, int b) {
		if (a>b) {
			return b;
		}
		return a;
	}

	@Override
	public User login(String id, String name, String password) {
		System.out.println(name+"is logging!");
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		return user;
	}

	
}
