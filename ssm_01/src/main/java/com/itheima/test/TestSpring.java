package com.itheima.test;

import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test1(){
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IAccountService service = as.getBean("accountService", IAccountService.class);
        service.findAll();
    }
}