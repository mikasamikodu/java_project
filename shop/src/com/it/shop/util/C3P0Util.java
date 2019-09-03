package com.it.shop.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	//建立一个数据源，方便从连接池中获取数据库连接，同时保持连接的速度
	private static ComboPooledDataSource database = new ComboPooledDataSource("oracle");
	
	//得到一个数据源
	public static ComboPooledDataSource getDatabase() {
		return database;
	}
	//从数据源中获取一个数据库连接
	public static Connection getConnection() {
		try {
			return database.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("获取连接失败！请检查你的c3p0-config配置文件。");
		}
	}
	
	public static void main(String args[]){
		System.out.println(111);
		System.out.println(C3P0Util.getConnection());
		System.out.println(22);
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
