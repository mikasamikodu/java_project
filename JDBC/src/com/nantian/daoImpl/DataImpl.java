package com.nantian.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nantian.dao.Data;
import com.nantian.entity.Student;
import com.nantian.util.BaseDao;

public class DataImpl implements Data {

	@Override
	public int add(Student student) {
		Connection conn = null;
		PreparedStatement state =null;
		int result = 0;
		try {
			conn = BaseDao.getConnection();
			state = conn.prepareStatement("insert into sa_opperson s(s.sid,s.sname,s.spassword) values(?,?,?)");
			state.setString(1, student.getId());
			state.setString(2, student.getName());
			state.setString(3, student.getPassword());
			result = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(String id) {
		Connection conn = null;
		PreparedStatement state =null;
		int result = 0;
		try {
			conn = BaseDao.getConnection();
			state = conn.prepareStatement("delete from sa_opperson s where s.sid=?");
			state.setString(1, id);
			result = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Student select1(String id) {
		Connection conn = null;
		PreparedStatement state =null;
		ResultSet result = null;
		Student student = null;
		try {
			conn = BaseDao.getConnection();
			state = conn.prepareStatement("select s.* from sa_opperson s where s.sid=?");
			state.setString(1, id);
			result = state.executeQuery();
			while(result.next()) {
				student = new Student();
				student.setId(result.getString("sid"));
				student.setName(result.getString("sname"));
				student.setPassword(result.getString("spassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student select2(String name) {
		Connection conn = null;
		PreparedStatement state =null;
		ResultSet result = null;
		Student student = null;
		try {
			conn = BaseDao.getConnection();
			state = conn.prepareStatement("select s.* from sa_opperson s where s.sname=?");
			state.setString(1, name);
			result = state.executeQuery();
			while(result.next()) {
				System.out.println(result.next());
				student = new Student();
				student.setId(result.getString("sid"));
				student.setName(result.getString("sname"));
				student.setPassword(result.getString("spassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

}
