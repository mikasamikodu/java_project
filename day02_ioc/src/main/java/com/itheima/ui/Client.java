package com.itheima.ui;

import com.itheima.service.IaccountService;
import com.itheima.service.IaccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        IaccountService service = app.getBean("IaccountService", IaccountService.class);
//        IaccountService service2 = app.getBean("IaccountService", IaccountService.class);
//        System.out.println(service);
        service.saveAccount();
        ((ClassPathXmlApplicationContext) app).close();
    }


}
