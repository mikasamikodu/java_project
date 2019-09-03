package com.itheima.dbutils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBUtils {
	private static DataSource source = null;
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(DBUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			source = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
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
}
