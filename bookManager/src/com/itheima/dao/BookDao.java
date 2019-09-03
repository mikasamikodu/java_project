package com.itheima.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.domain.Book;
import com.itheima.util.C3P0Util;
import com.itheima.util.UUIDUtil;

public class BookDao {
	
	/**
	 * 查询功能1
	 * @return
	 * @throws SQLException
	 */
	public List<Book> query() throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		List<Book> list = query.query("select * from book",new BeanListHandler<Book>(Book.class));
		return list;
	}
	
	/**
	 * 添加功能
	 * @param book
	 * @throws SQLException
	 */
	public void add(Book book) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		query.update("insert into book values(?,?,?,?,?,?)",book.getId(),book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription());
	}
	
	public static void main(String[] args) {
		Book book = new Book();
		
		book.setPrice(20);
		book.setPnum(30);
		book.setCategory("计算机");
		book.setDescription("java");
		BookDao dao = new BookDao();
		try {
			for(int i=0;i<100;i++) {
				book.setId(UUIDUtil.getUUID());
				book.setName("java"+i);
				dao.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------");
	}
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Book query2(String id) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		return query.query("select * from book where id=?",new BeanHandler<Book>(Book.class),id);
	}
	
	/**
	 * 修改
	 * @param book
	 * @throws SQLException
	 */
	public void update(Book book) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		query.update("update book set name=?,price=?,pnum=?,category=?,description=? where id=?",book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getId());
	}

	/**
	 * 删除
	 * @param book
	 * @throws SQLException
	 */
	public void delete(String id) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		query.update("delete from book where id=?",id);
	}
	
	/**
	 * 批量删除
	 * @param book
	 * @throws SQLException
	 */
	public void deleteAll(String[] checked) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		Object[][] params = new Object[checked.length][];
		for(int i=0;i<params.length;i++) {
			params[i] = new Object[]{checked[i]};
		}
		query.batch("delete from book where id=?", params);
	}
	
	/**
	 * 按条件查找
	 * @param book
	 * @throws SQLException
	 */
	public List<Book> query3(String id,String category,String name,String minprice,String maxprice) throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		String sql = "select * from book where 1=1 ";
		List<String> params = new ArrayList<String>(); 
		if(!"".equals(id.trim())) {
			sql += " and id like ?";
			params.add("%"+id.trim()+"%");
		}
		if(!"".equals(category)) {
			sql += " and category=?";
			params.add(category);
		}
		if(!"".equals(name.trim())) {
			sql += " and name like ?";
			params.add("%"+name.trim()+"%");
		}
		if(!"".equals(minprice.trim())) {
			sql += " and price>?";
			params.add(minprice.trim());
		}
		if(!"".equals(maxprice.trim())) {
			sql += " and price<?";
			params.add(maxprice.trim());
		}
		return query.query( sql, new BeanListHandler<Book>(Book.class), params.toArray());
	}
	
	public String search(String name) throws SQLException {
		String list = "";
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		List<Book> books = null;
		List<Object> booklist = new ArrayList<Object>();
		books = query.query("select name from book where name like '%"+name+"%'", new BeanListHandler<Book>(Book.class));
		if(books==null) {
			list = null;
		}else {
			for (Book book : books) {
				booklist.add(book.getName());
			}
			list = JSONArray.fromObject(booklist).toString();
			System.out.println(list);
		}
		return list;
	}
}