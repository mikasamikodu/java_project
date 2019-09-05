package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户
     *
     */
    List<Account> findAll();
    /**
     * 根据用户id查询所有相关账户
     * @param uid 用户id
     */
    List<Account> findByUid(Integer uid);
}
