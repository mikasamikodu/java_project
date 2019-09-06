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

    public static void main(String[] args) throws Exception{
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(in);
        SqlSession sql = factory.openSession();
        IUserDao dao = sql.getMapper(IUserDao.class);
//        List<User> users = dao.findAll();
//        for (User user: users) {
//            System.out.println(user);
//        }
//        User user = new User();
//        user.setUsername("tom3");
//        user.setAddress("天通苑");
//        user.setSex("男");
//        user.setBirthday(new Date());
//        dao.save(user);
//        User user = dao.findById(58);
//        System.out.println(user);
//        user.setUsername("tom31");
//        user.setAddress("天通苑");
//        user.setSex("男");
//        user.setBirthday(new Date());
//        dao.update(user);
//        System.out.println(dao.findById(58));
//        dao.delete(58);
//        List<User>  users = dao.findByName("%王%");
//        for (User user: users) {
//            System.out.println(user);
//        }
        System.out.println(dao.findCount());
        sql.commit();
        sql.close();
        in.close();
    }
}
