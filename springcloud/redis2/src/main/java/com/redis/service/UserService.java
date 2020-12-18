package com.redis.service;

import java.util.Map;

public interface UserService {

	/*
	 判断用户登录是否被限制
	 */
	public Map<String, Object> loginLock(String username);
	
	public boolean login(String username,String password);

	public String loginFail(String username);

	public void clearCount(String username);
}
