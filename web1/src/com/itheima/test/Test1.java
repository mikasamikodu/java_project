package com.itheima.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.itheima.dbutils.DBUtils;
import com.itheima.entity.Account;
import com.itheima.utils.C3P0Util;

public class Test1 {
	@Test
	public void test1() {
		Connection conn = null;
		conn = DBUtils.getConnection();
		System.out.println(conn);
		DBUtils.release( conn, null, null);
	}
	@Test
	public void test2() {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			conn = C3P0Util.getConnection();
			state = conn.prepareStatement("select * from account");
			result = state.executeQuery();
			while(result.next()) {
				System.out.println(result.getString(1)+'-'+result.getString(2)+'-'+result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		C3P0Util.release( conn, null, null);
	}
	@Test
	public void test3() throws Exception {
		QueryRunner query = new QueryRunner(C3P0Util.getData());
		List<Account> userList = query.query("select * from account", new ResultSetHandler<List<Account>>() {

			@Override
			public List<Account> handle(ResultSet result) throws SQLException {
				List<Account> list = new ArrayList<Account>();
				while(result.next()) {
					Account user = new Account();
					user.setId(result.getString(1));
					user.setName(result.getString(2));
					user.setMoney(result.getDouble(3));
					list.add(user);
				}
				return list;
			}});
		for(Account users:userList) {
			System.out.println(users.toString());
		}
	}
	@Test
	public void test4() throws Exception {
		QueryRunner query = new QueryRunner(C3P0Util.getData());
		List<Account> list = query.query("select * from account", new BeanListHandler<Account>(Account.class));
		for(Account users:list) {
			System.out.println(users.toString());
		}
	}
}
