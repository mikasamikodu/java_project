package com.itheima.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	private static DataSource data = new ComboPooledDataSource("mysql");
	
	public static DataSource getData() {
		return data;
	}

	public static Connection getConnection() {
		try {
			return data.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器初始化错误！");
		}
	}
	
	public static void release( Connection conn, Statement state, ResultSet result) {
		if(result!=null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			result = null;
		}
		if(state!=null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			state = null;
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	public static void main(String args[]) {
		Connection conn = null;
		conn = C3P0Util.getConnection();
		System.out.println(conn);
		C3P0Util.release(conn, null, null);
		
	}
}
