package com.itheima;

import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        AccountService service = (AccountService)app.getBean("accountService");
        service.saveAccount();
//        service.updateAccount(1);
//        service.deleteAccount();想·
    }
}
