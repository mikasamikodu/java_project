package com.itheima.service;

public interface AccountService {

	/**
	 * 更新用户信息
	 * @param from 来源用户
	 * @param to 目标用户
	 * @param money 更新的信息
	 */
	public void transfer(String from,String to,double money);
}
