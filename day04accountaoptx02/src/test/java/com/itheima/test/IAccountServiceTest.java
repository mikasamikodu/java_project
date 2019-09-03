package com.itheima.test;

import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
/*
* spring与junit的配置
* 1.导入spring整合junit的jar坐标
* 2.使用junit提供的注解把原来的main方法替换，替换成spring提供的注解
*   @Runwith
* 3.告知spring，spring和ioc的穿件是基于xml还是注解，并说明位置，使用注解@ContextConfiguration,
*   相关属性：location--指定xml文件位置，加上classpath关键字，表示在类路径下
*            classes--指定注解类所在位置
* 在使用 spirng5.x时，要求junit的jar版本需要4.12以上
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class IAccountServiceTest {

    @Autowired
    IAccountService accountService;

    @Test
    public void testFindById(){
        IAccount account = accountService.findById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdate(){
        accountService.transfer("aaa", "bbb", 100f);
    }

}
