package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;

import java.util.List;

public class IAccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<IAccount> findAllAccount(){
        return accountDao.findAllAccount();
    }

    public IAccount findById(Integer id) {
        return accountDao.findById(id);
    }

    public void saveAccount(IAccount account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(IAccount account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }
}
