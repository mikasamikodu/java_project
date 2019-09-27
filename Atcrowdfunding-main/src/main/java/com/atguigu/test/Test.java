package com.atguigu.test;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.User;
import com.atguigu.manager.service.UserService;

public class Test {
	
	@SuppressWarnings("resource")
	public static void main(String[]args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/spring*.xml");
		UserService service = app.getBean(UserService.class);
		for(int i=0;i<100;i++) {
			User user = new User();
			user.setCreatetime("2019-09-27 11:12:11");
			user.setEmail("test"+i+"@qq.com");
			user.setUserpswd("123");
			user.setUsername("test"+i);
			user.setLoginacct("test"+i);
			service.saveUser(user);
		}
	}

}
