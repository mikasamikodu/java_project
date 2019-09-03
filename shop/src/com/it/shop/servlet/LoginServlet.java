package com.it.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.shop.domain.User;
import com.it.shop.exception.UserException;
import com.it.shop.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		/* 设置响应头允许ajax跨域访问 */  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        /* 星号表示所有的异域请求都可以接受， */  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //获取用户名与密码
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		//处理业务逻辑
		UserService service = new UserService();
		User user = null;
		PrintWriter out = new PrintWriter(response.getOutputStream());
		try {
			user = service.login(uname);
			System.out.println(222);
			if(!user.getUpassword().equals(password)) {
				//密码错误，返回3
				throw new UserException("3");
			}
			request.getSession().setAttribute("user", user);
			System.out.println(111);
			//登录成功返回1
			out.print(1);
	        out.flush(); 
		} catch (UserException e) {
			out.print(e.getMessage());
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
