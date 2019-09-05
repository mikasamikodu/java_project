package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleTest {

    private InputStream in = null;
    private SqlSession sql = null;
    private IRoleDao dao = null;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        sql = new SqlSessionFactoryBuilder().build(in).openSession();
        dao = sql.getMapper(IRoleDao.class);
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

    @Test//测试查询所有角色信息的方法
    public void testfindAll(){
        List<Role> roles = dao.findAll();
        for(Role role: roles){
            System.out.println("-------------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

}
