package com.nantian.soap2;

import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface="com.nantian.soap2.MyService")
@HandlerChain(file="handler-chain.xml")
public class MyServiceImpl implements MyService {

	private List<User> list = new ArrayList<User>();
	
	public MyServiceImpl() {
		User user = new User("1","张三","管理员","1234");
		list.add(user);
	}
	@Override
	public int add(int a, int b) {
		System.out.println(a+"+"+b+"="+(a+b));
		return a+b;
	}

	@Override
	public User addUser(User user) {
		list.add(user);
		return user;
	}

	@Override
	public User login(String username, String password) throws UserException{
		for(User user:list) {
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new UserException("用户不存在");
	}

	@Override
	public List<User> list(String authInfo) {
		System.out.println(authInfo);
		return list;
	}

}
