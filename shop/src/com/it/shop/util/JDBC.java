package com.it.shop.util;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class JDBC {
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String URL="jdbc:oracle:thin:@localhost:1521:orcl?useUnicode=true&amp;characterEncoding=utf-8";
        String USER="fams";
        String PASSWORD="fams";
        //1.加载驱动程序
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2.获得数据库链接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(conn);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from oa_as_card");
        //4.处理数据库的返回结果(使用ResultSet类)
        while(rs.next()){
        	System.out.println(1111111);
            System.out.println(rs.getString("fcode")+" "
                          +rs.getString("fkind"));
            break;
        }
        
        //关闭资源
        rs.close();
        st.close();
        conn.close();
    }
}
