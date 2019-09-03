package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    Account findById(int accountId);

    Account findByName(String accountName);

    void update(Account account);

    void delete(int accountId);

    void save(Account account);
}
