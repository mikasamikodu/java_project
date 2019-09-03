package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

public class IAccountDaoImpl extends JdbcDaoSupport implements IAccountDao {


    public IAccount findById(Integer id) {
        List<IAccount> accounts = null;
        try{
            accounts = super.getJdbcTemplate().query("select * from account where id=?", new BeanPropertyRowMapper<IAccount>(IAccount.class), id);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
        return accounts.get(0);
    }

    public IAccount findByName(String name) {
        List<IAccount> accounts = null;
        try{
            accounts = super.getJdbcTemplate().query("select * from account where name=?", new BeanPropertyRowMapper<IAccount>(IAccount.class), name);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
        return accounts.get(0);
    }


    public void updateAccount(IAccount account) {
        try{
            super.getJdbcTemplate().update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
