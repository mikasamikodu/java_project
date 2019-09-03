package com.itheima.service;

import com.itheima.domain.IAccount;

import java.util.List;

/*
业务层
* */
public interface IAccountService {

    /*查询所有*/
    public List<IAccount> findAllAccount();

    /*查询一个*/
    public IAccount findById(Integer id);
    /*保存账户*/
    public void saveAccount(IAccount account);
    /*更新账户*/
    public void updateAccount(IAccount account);
    /*删除账户*/
    public void deleteAccount(Integer id);
}
