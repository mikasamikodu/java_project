package com.nantian.soap2;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyService {
	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);
	@WebResult(name="user")
	public User addUser(@WebParam(name="user")User user);
	@WebResult(name="user")
	public User login(@WebParam(name="username")String username,
					  @WebParam(name="password")String password) throws UserException;
	@WebResult(name="user")
	public List<User> list(@WebParam(header=true,name="authInfo")String authInfo);
}
