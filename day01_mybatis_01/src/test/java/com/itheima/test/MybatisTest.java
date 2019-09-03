package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    public static void main(String[] args) throws Exception{
        //1.读取配置文件
        InputStream  in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建sqlsessionfactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        //3. 使用工厂生产sqlsession对象
        SqlSession session = sqlSessionFactory.openSession();
        //4.使用sqlsession创建dao接口的地代理对象
        IUserDao dao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = dao.findAll();
        for(User user: users){
            System.out.println(user);
        }
        session.close();
        in.close();
    }
}
