package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.BookDao;
import com.itheima.domain.Book;

public class BookService {

	BookDao dao = new BookDao();
	
	public List<Book> findAllBook() {
		try {
			return dao.query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addBook(Book book) {
		try {
			dao.add(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Book findBookByID(String id) {
		try {
			return  dao.query2(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateBook(Book book) {
		try {
			dao.update(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(String id) {
		try {
			dao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllBook(String[] checked) {
		try {
			dao.deleteAll(checked);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> FindBook(String id,String category,String name,String minprice,String maxprice) {
		try {
			return dao.query3(id,category,name,minprice,maxprice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchAJAX(String name) {
		String list = null;
		try {
			list = dao.search(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
