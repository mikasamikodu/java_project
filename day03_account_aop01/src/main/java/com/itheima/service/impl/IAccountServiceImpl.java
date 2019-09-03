package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import com.itheima.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void transfer(String sourceName, String targetName, float money) {
            System.out.println("begin");
            IAccount account1 = accountDao.findByName(sourceName);
            IAccount account2 = accountDao.findByName(targetName);
            account1.setMoney(account1.getMoney()-money);
            account2.setMoney(account2.getMoney()+money);
            accountDao.updateAccount(account1);
            accountDao.updateAccount(account2);
        System.out.println("end");
    }
}
