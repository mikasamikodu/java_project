package com.itheima.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class ServletContextTest implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//得到servletcontext对象
				ServletContext context = arg0.getServletContext();
				//创建用于存储session对象的容器list
				final List<HttpSession> list = Collections.synchronizedList(new ArrayList<HttpSession>());
				//将list放入servletcontext
				context.setAttribute("list", list);
				//添加定时任务
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					
					@Override
					public void run() {
						System.out.println("开始扫描了！！！");
						for (Iterator<HttpSession> iterator = list.iterator(); iterator.hasNext();) {
							HttpSession session = (HttpSession) iterator.next();
							long t = System.currentTimeMillis()-session.getLastAccessedTime();
							if(t>5000) {
								System.out.println("Session"+session.getId()+"被移除从容器中！");
								session.invalidate();
								iterator.remove();
							}
						}
					}
				}, 2000, 5000);
	}

}
