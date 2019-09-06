package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream in = null;
    SqlSessionFactory factory =  null;
//    private SqlSession sql = null;
//    private IUserDao dao = null;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        factory =  new SqlSessionFactoryBuilder().build(in);
//        sql = factory.openSession();
//        dao = sql.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
//        sql.commit();
//        if(sql!=null){
//            sql.close();
//        }
        if(in!=null){
            in.close();
        }
    }

    @Test//测试查询所有用户信息的方法
    public void testTheFirstCache(){
        SqlSession sql1 = factory.openSession();
        IUserDao dao1 = sql1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        sql1.close();
        SqlSession sql2 = factory.openSession();
        IUserDao dao2 = sql2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user1==user2);
    }
}
