package com.atguigu.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("应用启动了");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("应用销毁了");
    }
}
