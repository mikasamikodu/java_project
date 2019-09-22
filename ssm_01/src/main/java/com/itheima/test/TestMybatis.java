package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void testFindAll() throws Exception{
  /*      InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        List<Account> accounts = dao.findAll();
        for(Account account: accounts){
            System.out.println(account);
        }
        in.close();
        sqlSession.close();*/
    }
    @Test
    public void testSaveAccount() throws Exception{
       /* InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        Account account = new Account();
        account.setName("李斯");
        account.setMoney(133d);
        dao.saveAccount(account);
        sqlSession.commit();
        sqlSession.close();
        in.close();*/
    }
}