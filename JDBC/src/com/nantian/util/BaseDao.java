package com.nantian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.nantian.entity.Student;

public class BaseDao {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	
	  //生产
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username="fams";
	private static String password="fams";
	 
	// 测试
	/*private static String url = "jdbc:oracle:thin:@localhost:1521:fams";
	private static String username = "sdicfams";
	private static String password = "sdicfams";*/
	// 本地216
	/*private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username = "sdic";
	private static String password = "sdic";*/
	private static Connection conn = null;
	private static PreparedStatement pstm = null;
	private static ResultSet rs = null;

	public static Connection getConnection() {
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

	public static ResultSet executeQuery(String sql, Object[] params) {
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

//	public static int executeSQL(String sql, Object[] param) throws Exception {
//		int num = 0;
//		try {
//			conn = getConnection();
//			pstm = conn.prepareStatement(sql);
//			if (param != null) {
//				for (int i = 0; i < param.length; i++) {
//					pstm.setObject(i + 1, param[i]);
//				}
//			}
//			num = pstm.executeUpdate();
//		} catch (SQLException e) {
//			num = -1;
//			e.printStackTrace();
//
//		} finally {
//			getConnectionClose(conn, pstm, rs);
//		}
//		return num;
//	}

	public static void getConnectionClose(Connection conn, PreparedStatement pstm, ResultSet rs) {
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
	
//	public static void main(String[]args) {
//			Connection conn = null;
//			PreparedStatement state =null;
//			ResultSet result = null;
//			Student student = null;
////			String id = "张三";
//			try {
//				conn = getConnection();
//				state = conn.prepareStatement("select * from sa_opperson where sname='韩东军'");
//				result = state.executeQuery();
//				while(result.next()) {
//					student = new Student();
//					student.setId(result.getString("sid"));
//					student.setName(result.getString("sname"));
//					student.setPassword(result.getString("spassword"));
//				}
////				System.out.println(student);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				getConnectionClose(conn, state, result);
//			}
//			System.out.println(student);
//	}

}
