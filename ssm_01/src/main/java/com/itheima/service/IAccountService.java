package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户
     * @param account
     */
    public void saveAccount(Account account);
}
