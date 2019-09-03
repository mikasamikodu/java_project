package com.it.shop.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it.shop.domain.User;
import com.it.shop.util.C3P0Util;

public class UserDao {

	//通过用户名与密码查找用户
	public User findUserByUnameAndPassword(String uname) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDatabase());
		String sql = "select * from user_infom where uaccount=?";
		return query.query(sql, new BeanHandler<User>(User.class), uname);
	}

	public void addUser(User user) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDatabase());
		String sql = "INSERT INTO USER_INFOM (id,uname,uaccount,UPASSWORD)VALUES(?,?,?,?)";
		System.out.println(sql);
		query.update(sql, user.getId(), user.getUaccount(), user.getUaccount(), user.getUpassword());
	}
}
