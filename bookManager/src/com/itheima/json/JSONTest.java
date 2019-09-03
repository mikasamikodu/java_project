package com.itheima.json;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.itheima.domain.Book;
import com.itheima.util.C3P0Util;

public class JSONTest {

	@Test
	public void test1() {
		Book book = new Book();
		book.setId("1001");
		book.setName("tom");
		book.setPrice(20);
		String books = JSONArray.fromObject(book).toString();
		System.out.println(books);
	}
	
	@Test
	public void test2() {
		Book book = new Book();
		book.setId("1001");
		book.setName("tom");
		book.setPrice(20);
		
		Book book1 = new Book();
		book1.setId("1001");
		book1.setName("tom");
		book1.setPrice(20);
		
		Book book2 = new Book();
		book2.setId("1001");
		book2.setName("tom");
		book2.setPrice(20);
		
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		list.add(book1);
		list.add(book2);
		String books = JSONArray.fromObject(list).toString();
		System.out.println(books);
	}
	
	@Test
	public void test3() throws SQLException {
		QueryRunner query = new QueryRunner(C3P0Util.getDs());
		List<Book> list = query.query("select * from book", new BeanListHandler<Book>(Book.class));
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"category","pnum","description","id"});
		String books = JSONArray.fromObject(list, config).toString();
		System.out.println(books);
	}
	
}
