package com.itheima.test;

import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class IAccountServiceTest {

    private ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    IAccountService service = ac.getBean("accountService", IAccountService.class);
    @Test
    public void testFindAll(){

        List<IAccount> list = service.findAllAccount();
        if(list==null){
            System.out.println("11111");
        }else {
            for (IAccount account : list) {
                System.out.println(account);
            }
        }

    }

    @Test
    public void testFindById(){
        IAccount account = service.findById(3);
        System.out.println(account);
    }

    @Test
    public void testUpdate(){
        IAccount account = service.findById(1);
        System.out.println(account);
        account.setName("tom1");
        account.setMoney(2001);
        service.updateAccount(account);
        System.out.println(service.findById(1));
    }

    @Test
    public void testDetele(){
        service.deleteAccount(5);
    }

    @Test
    public void testSave(){
        IAccount account = new IAccount();

        account.setName("tom");
        account.setMoney(2000);
        service.saveAccount(account);
    }
}
