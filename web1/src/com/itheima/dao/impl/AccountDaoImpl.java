package com.itheima.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.AccountDao;
import com.itheima.entity.Account;
import com.itheima.utils.C3P0Util;

public class AccountDaoImpl implements AccountDao {

	@Override
	public void updateAccount(String from, String to, double money) throws Exception {
		QueryRunner query = new QueryRunner(C3P0Util.getData());
		query.update("update account set money=money-? where name=?",money,from);
		query.update("update account set money=money+? where name=?",money,to);
	}

	@Override
	public void updateAccount2(Account user) throws Exception{
		QueryRunner query = new QueryRunner();
		query.update(C3P0Util.getConnection(),"update account set money=? where name=?",user.getMoney(),user.getName());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Account findAccountByNname(String name) throws Exception {
		QueryRunner query = new QueryRunner();
		Account account = query.query(C3P0Util.getConnection(),"select * from account where name=?",name,new BeanHandler<Account>(Account.class));
		return account;
	}

}
