package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.BookService;

public class SearchBooksAJAXServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求与响应的格式，以防乱码；
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取表单数据
		String name = request.getParameter("name");
		//处理业务逻辑
		BookService book = new BookService();
		String list = book.searchAJAX(name);
		//分发转向
		//request.setAttribute("name", list);
		//request.getRequestDispatcher("/menu_search.jsp").forward(request, response);
		response.getWriter().print(list);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
