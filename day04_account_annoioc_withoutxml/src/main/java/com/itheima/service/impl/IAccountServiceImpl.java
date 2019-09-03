package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="accountService")
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

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
