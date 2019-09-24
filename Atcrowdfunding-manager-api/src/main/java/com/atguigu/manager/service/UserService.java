package com.atguigu.manager.service;

import java.util.Map;

import com.atguigu.bean.User;

public interface UserService {

	User queryUserLogin(Map<String, Object> map);

}
