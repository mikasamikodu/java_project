package com.itheima.jdbcTemplate;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.dao.impl.AccountDaoImpl2;
import com.itheima.dao.impl.AccountDaoImpl3;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        AccountDao dao = app.getBean("accountDao", AccountDaoImpl3.class);
        List<Account> accounts = dao.findAll();
        for(Account account:accounts){
            System.out.println(account);
        }
    }
}
