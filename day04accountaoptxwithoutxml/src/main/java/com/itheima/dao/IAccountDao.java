package com.itheima.dao;

import com.itheima.domain.IAccount;

import java.util.List;

public interface IAccountDao {

    /*查询一个*/
    public IAccount findById(Integer id);
    /*查询一个*/
    public IAccount findByName(String name);
    /*更新账户*/
    public void updateAccount(IAccount account);
}
