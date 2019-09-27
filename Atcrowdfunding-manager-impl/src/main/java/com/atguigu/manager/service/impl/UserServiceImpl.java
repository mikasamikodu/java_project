package com.atguigu.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.exception.LoginFailException;
import com.atguigu.bean.Member;
import com.atguigu.bean.User;
import com.atguigu.manager.dao.UserMapper;
import com.atguigu.manager.service.UserService;
import com.atguigu.utils.Page;

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

	@Override
	public void doRegister(Member member) {
		User user = new User();
		user.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		user.setEmail(member.getEmail());
		user.setLoginacct(member.getLoginacct());
		user.setUsername(member.getUsername());
		user.setUserpswd(member.getUserpswd());
		userMapper.doRegister(member);
		userMapper.insert(user);
	}

	@Override
	public Page queryPage(Integer pageNo, Integer pageSize) {
		Page page = new Page(pageNo, pageSize);
		Integer startIndex = page.getStartIndex();
		List<User> users = userMapper.queryList(startIndex, pageSize);
		page.setDatas(users);
		page.setTotalSize(userMapper.queryCount());
		return page;
	}

	@Override
	public void saveUser(User user) {
		userMapper.insert(user);		
	}
}
