package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in = null;
    private SqlSession sql = null;
    private UserDaoImpl dao = null;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        sql = new SqlSessionFactoryBuilder().build(in).openSession();
        dao = new UserDaoImpl(sql);
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
    public void testFindAll(){
        List<User> users = dao.findAll();
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test//测试根据条件查询用户信息的方法
    public void testFindByCondition(){
        User u = new User();
        u.setUsername("老王");
        u.setSex("女");
        List<User> users = dao.findByCondition(u);
        for(User user: users){
            System.out.println(user);
        }
    }
    @Test//测试根据条件查询用户信息的方法
    public void testFindByList(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setList(list);
        List<User> users = dao.findByList(vo);
        for(User user: users){
            System.out.println(user);
        }
    }


    @Test//测试通过id查询用户信息的方法
    public void testFindById(){
        User user = dao.findById(42);
        System.out.println(user);
    }

    @Test//测试通过name查询用户信息的方法
    public void testFindByName(){
        List<User> users = dao.findByName("%王%");
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test//测试保存用户信息的方法
    public void testSave(){
        User user = new User();
        user.setUsername("jack2");
        user.setBirthday(new Date());
        user.setAddress("北京");
        user.setSex("男");
        System.out.println("保存之前"+user);
        dao.save(user);
        System.out.println(user);
    }

    @Test//测试更新用户信息的方法
    public void testUpdate(){
        User user = new User();
        user.setId(41);
        user.setUsername("老王");
        user.setBirthday(new Date());
        user.setAddress("北京");
        user.setSex("女");
        dao.update(user);
    }

    @Test//测试删除用户信息的方法
    public void  testDelete(){
        dao.delete(53);
    }

    @Test//测试用户数量的方法
    public void  testFindTotal(){
        System.out.println(dao.findTotal());
    }
}
