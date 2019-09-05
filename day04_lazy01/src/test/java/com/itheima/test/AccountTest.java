package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {

    private InputStream in = null;
    private SqlSession sql = null;
    private IAccountDao dao = null;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        sql = new SqlSessionFactoryBuilder().build(in).openSession();
        dao = sql.getMapper(IAccountDao.class);
    }

    @After
    public void destory() throws Exception{
        sql.commit();
        if(sql!=null){
            sql.close();
        }
        if(in!=null){
            in.close();
        }
    }

    @Test//测试查询所有用户信息的方法
    public void testfindAll(){
        List<Account> accounts = dao.findAll();
        for(Account account: accounts){
            System.out.println("-------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

}
