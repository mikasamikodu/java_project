package com.nantian.data;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Data {
	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);
	@WebResult(name="minResult")
	public int min(@WebParam(name="a")int a,@WebParam(name="b")int b);
	@WebResult(name="loginUser")
	public User login(@WebParam(name="id")String id,@WebParam(name="name")String name,@WebParam(name="password")String password);
}
