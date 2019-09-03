package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.JdbcDaoSupport;
import com.itheima.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {

    public List<Account> findAll() {
        return super.getJdbcTemplate().query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
    }

    public Account findById(int accountId) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id=?", new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    public Account findByName(String accountName) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class), accountName);
        if(accounts==null){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    public void update(Account account) {
        super.getJdbcTemplate().update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
    }

    public void delete(int accountId) {
        super.getJdbcTemplate().update("delete from account where id=?", accountId);
    }

    public void save(Account account) {
        super.getJdbcTemplate().update("insert into account (name,money) values(?,?)", account.getName(), account.getMoney());
    }
}
