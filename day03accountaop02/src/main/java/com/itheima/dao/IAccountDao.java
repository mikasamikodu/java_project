package com.itheima.dao;

import com.itheima.domain.IAccount;

import java.util.List;

public interface IAccountDao {
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

    /**
     * 通过姓名查询账户
     * @param name
     * @return
     */
    public IAccount findByName(String name);
}
