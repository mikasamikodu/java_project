package dao.impl;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	private static final String DRIVER="org.sqlite.JDBC";
	private static final String URL="jdbc:sqlite:d:\\sqlite\\data.db";
	
	public Connection getconn() {
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeAll(ResultSet result,PreparedStatement state,Connection conn) {
		try {
			if(result!=null) {
				result.close();
			}
			if(state!=null) {
				state.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean operUpdate(String sql,List<Object> list) {
		int res=0;
		PreparedStatement state=null;
		ResultSet result=null;
		Connection conn=null;
		try {
			conn=getconn();
			state=conn.prepareStatement(sql);
			if(list!=null) {
				int leng=list.size();
				for(int i=0;i<leng;i++) {
					state.setObject(i+1, list.get(i));
				}
			}
			res=state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(result, state, conn);
		}
		return res>0?true:false;
	}
	
	public <T>List<T> operQuery(String sql,List<Object> list,Class<T>  type) throws Exception{
		PreparedStatement state=null;
		ResultSet result=null;
		Connection conn=null;
		List<T> data=new ArrayList<T>();
		try {
			conn=getconn();
			state=conn.prepareStatement(sql);
			if(list!=null) {
				int leng=list.size();
				for(int i=0;i<leng;i++) {
					state.setObject(i+1, list.get(i));
				}
			}
			result=state.executeQuery();
			ResultSetMetaData set=result.getMetaData();
			while(result.next()) {
				T meta=type.newInstance();
				for(int m=0;m<set.getColumnCount();m++) {
					String col_name=set.getColumnName(m+1);
					Object value=result.getObject(col_name);
					Field file=type.getDeclaredField(col_name);
					file.setAccessible(true);
					file.set(meta, value);
				}
				data.add(meta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(result, state, conn);
		}
		return  data;
	}
}
