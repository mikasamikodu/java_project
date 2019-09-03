package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class IAccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<IAccount> findAllAccount(){
        try{
            return runner.query("select * from account", new BeanListHandler<IAccount>(IAccount.class));
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IAccount findById(Integer id) {
        try{
            return runner.query("select * from account where id=?", new BeanHandler<IAccount>(IAccount.class), id);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(IAccount account) {
        try{
            runner.update("insert into account(name,money) values(?,?)", account.getName(), account.getMoney());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(IAccount account) {
        try{
            runner.update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer id) {
        try{
            runner.update("delete from  account where  id=?", id);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
