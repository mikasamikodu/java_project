package com.itheima.util;

import java.sql.*;

public class DBUtils {

    public static void getConntion() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day13", "root" , "root");
        PreparedStatement sql = conn.prepareStatement("select * from account");
        ResultSet result = sql.executeQuery();
        while(result.next()){
            System.out.println(result.getString("name"));
        }

    }
}
