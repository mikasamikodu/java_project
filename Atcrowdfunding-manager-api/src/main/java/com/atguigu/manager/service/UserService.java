package com.atguigu.manager.service;

import java.util.Map;

import com.atguigu.bean.Member;
import com.atguigu.bean.User;
import com.atguigu.utils.Page;

public interface UserService {

	User queryUserLogin(Map<String, Object> map);

	void doRegister(Member member);

	Page queryPage(Integer pageNo, Integer pageSize);

	void saveUser(User user);

}
