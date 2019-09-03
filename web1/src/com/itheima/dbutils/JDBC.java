package com.itheima.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBC {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day13","root","root");
			state = conn.prepareStatement("select * from account");
			result = state.executeQuery();
			while(result.next()) {
				System.out.println(result.getString(1)+'-'+result.getString(2)+'-'+result.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(conn);
		DBUtils.release( conn, null, null);
	}
}
