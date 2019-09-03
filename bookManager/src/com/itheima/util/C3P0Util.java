package com.itheima.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/*
 * 首先加入jar包
 * 然后建立c3p0类，选择建立方式(基础的方式或.properties或.xml),
 * 如果选择.properties或.xml则需要先引入文件，然后编写getConnection()和release()方法
 * 最后编写测试类
 */
public class C3P0Util {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	
	public static ComboPooledDataSource getDs() {
		return ds;
	}

	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器出错");
		}
	}
	
	public static void release(ResultSet rs,Statement stm,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stm=null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
}
