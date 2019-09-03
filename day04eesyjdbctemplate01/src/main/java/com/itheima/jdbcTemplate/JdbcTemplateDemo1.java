package com.itheima.jdbcTemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
//        DriverManagerDataSource data = new DriverManagerDataSource();
//        data.setDriverClassName("com.mysql.jdbc.Driver");
//        data.setUrl("jdbc:mysql://localhost:3306/day13");
//        data.setUsername("root");
//        data.setPassword("root");
//        JdbcTemplate jdbc = new JdbcTemplate();
//        jdbc.setDataSource(data);
//        jdbc.execute("insert into account (name,money) values('ccc', 1000)");
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = app.getBean("jdbcTemplate", JdbcTemplate.class);
//        jdbcTemplate.execute("insert into account (name,money) values('ddd', 1000)");
        //保存
//        jdbcTemplate.update("insert into account (name, money) values(?, ?)", "test", 1000f);
        //更新
//        jdbcTemplate.update("update account set name=?, money=? where name=?", "fff", 1001f, "ccc");
        // 删除
//        jdbcTemplate.update("delete from account where name=?", "fff");
        //查询所有
//        List<Account> accounts =  jdbcTemplate.query("select * from account where money>?", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
//        for(Account account: accounts){
//            System.out.println(account);
//        }
//        List<Account> accounts =  jdbcTemplate.query("select * from account where money=?", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
//        System.out.println(accounts.isEmpty()?"没有内容。":accounts.get(0));
        int count = jdbcTemplate.queryForObject("select count(*) from account where money=?", int.class, 1000f);
        System.out.println(count);
    }


    class AccountRowMapper implements RowMapper<Account>{
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString(":name"));
            account.setMoney(resultSet.getFloat("money"));
            return account;
        }
    }
}
