package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	
//	  //生产
//	private static String url="jdbc:oracle:thin:@10.0.196.111:1521:prod";
//	private static String username="sdicfams";
//	private static String password="sdicfams";
	 
	// 测试
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username = "fams";
	private static String password = "fams";
	// 本地216
	/*private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username = "sdic";
	private static String password = "sdic";*/
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ResultSet executeQuery(String sql, Object[] params) {
		conn = getConnection();
		try {
			pstm = conn.prepareStatement(sql);

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
			rs = pstm.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rs;
	}

	public int executeSQL(String sql, Object[] param) throws Exception {
		int num = 0;
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstm.setObject(i + 1, param[i]);
				}
			}
			num = pstm.executeUpdate();
		} catch (SQLException e) {
			num = -1;
			e.printStackTrace();

		} finally {
			getConnectionClose(conn, pstm, rs);
		}
		return num;
	}

	public void getConnectionClose(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		BaseDao dao = new BaseDao();
		System.out.println(dao.getConnection());
	}

}
