package com.itheima.test;

import com.itheima.dao.IUserDao;
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
import java.util.Date;
import java.util.List;

public class MybatisTest {

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
            System.out.println(user);
        }
    }

    @Test//测试通过id查询用户信息的方法
    public void testfindById(){
        User user = dao.findById(41);
        System.out.println(user);
    }

    @Test//测试通过queryVo查询用户信息的方法
    public void testfindByVo(){
        QueryVo vo = new QueryVo();
        User u = new User();
        u.setUsername("%王%");
        vo.setUser(u);
        List<User> users = dao.findByVo(vo);
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test//测试通过name查询用户信息的方法
    public void testfindByName(){
        List<User> users = dao.findByName("%王%");
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test//测试保存用户信息的方法
    public void testSave(){
        User user = new User();
        user.setUsername("jack last insertid");
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
        user.setId(53);
        user.setUsername("rose");
        user.setBirthday(new Date());
        user.setAddress("沈阳");
        user.setSex("女");
        dao.update(user);
    }

    @Test//测试删除用户信息的方法
    public void  testDelete(){
        dao.delete(52);
    }

    @Test//测试用户数量的方法
    public void  testFindTotal(){
        System.out.println(dao.findTotal());
    }
}
