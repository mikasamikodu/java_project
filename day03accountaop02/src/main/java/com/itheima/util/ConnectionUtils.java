package com.itheima.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
    @Autowired
    private DataSource dataSource;

    /**
     * 将连接与线程进行绑定
     * @return
     */
    public  Connection getConnection(){
        try{
            //首先从线程中获取连接
            Connection conn = thread.get();
            //检查连接是否为空，
            if(conn == null){
                //空则从数据库连接池中取出一个，并放入线程中
                conn = dataSource.getConnection();
                thread.set(conn);
            }
            //返回连接
            return conn;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 解除线程与连接的绑定
     */
    public void removeConnection(){
        thread.remove();
    }
}
