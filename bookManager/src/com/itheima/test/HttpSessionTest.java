package com.itheima.test;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionTest implements HttpSessionListener {

	@SuppressWarnings("unchecked")
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		//得到servletcontext对象
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		//向list中添加session对象
		List<HttpSession> list = (List<HttpSession>) context.getAttribute("list");
		list.add(session);
		System.out.println("Session"+session.getId()+"被添加入容器中！");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

	}

}
