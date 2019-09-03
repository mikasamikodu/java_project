package com.it.shop.service;

import java.sql.SQLException;

import com.it.shop.dao.UserDao;
import com.it.shop.domain.User;
import com.it.shop.exception.UserException;

public class UserService {

	UserDao dao = new UserDao();
	public User login(String uname) throws UserException {
		User user = null;
		try {
			user = dao.findUserByUnameAndPassword(uname);
			if(user==null) {
				//用户名不存在，返回2
				throw new UserException("2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void regist(User user) throws UserException {
		try {
			dao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("2");
		}
		
	}
}
