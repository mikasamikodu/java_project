package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.entity.Account;
import com.itheima.service.AccountService;

public class AccountServiceImpl implements AccountService {

	AccountDao user = new AccountDaoImpl();
	@Override
	public void transfer(String from, String to, double money) {
		try {
			Account user1 = user.findAccountByNname(from);
			Account user2 = user.findAccountByNname(to);
			user1.setMoney(user1.getMoney()-money);
			user2.setMoney(user2.getMoney()+money);
			user.updateAccount2(user1);
			user.updateAccount2(user2);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
