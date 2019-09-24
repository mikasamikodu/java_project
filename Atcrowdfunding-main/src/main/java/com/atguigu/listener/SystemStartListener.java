package com.atguigu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemStartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("APP_PATH", path);
		System.out.println("SystemStartListener监听器将上下文放入application域中了。。。");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

}
