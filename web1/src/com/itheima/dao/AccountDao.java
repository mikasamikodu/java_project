package com.itheima.dao;

import com.itheima.entity.Account;

public interface AccountDao {
	/**
	 * 更新用户信息
	 * @param from 来源用户
	 * @param to 目标用户
	 * @param money 更新的信息
	 */
	@Deprecated
	public void updateAccount(String from,String to,double money) throws Exception; 
	
	/**
	 * 根据用户信息修改金额
	 * @param user 用户
	 */
	public void updateAccount2(Account user) throws Exception;
	
	public Account findAccountByNname(String name) throws Exception;
}
