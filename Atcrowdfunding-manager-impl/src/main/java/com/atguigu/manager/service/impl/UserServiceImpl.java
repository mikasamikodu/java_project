package com.atguigu.manager.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.exception.LoginFailException;
import com.atguigu.bean.User;
import com.atguigu.manager.dao.UserMapper;
import com.atguigu.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryUserLogin(Map<String, Object> map) {
		User user = userMapper.queryUserlogin(map);
		if(user == null) {
			throw new LoginFailException("用户名或密码不正确");
		}
		return user;
	}
}
