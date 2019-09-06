package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream in = null;
    private SqlSession sql = null;
    private IUserDao dao = null;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        sql = new SqlSessionFactoryBuilder().build(in).openSession();
        dao = sql.getMapper(IUserDao.class);
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
        List<User> users = dao.findAll();
        for(User user: users){
            System.out.println("-------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test//测试通过id查询用户信息的方法
    public void testfindById(){
        User user = dao.findById(41);
        System.out.println(user);
    }
}
