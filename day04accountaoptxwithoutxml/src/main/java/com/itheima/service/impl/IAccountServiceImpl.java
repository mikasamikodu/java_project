package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("accountService")
@Transactional
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public IAccount findById(int id){
        return accountDao.findById(id);
    }

    public void transfer(String source,String target,float money) {
        IAccount account1 = new IAccount();
        IAccount account2 = new IAccount();
        account1.setMoney(account1.getMoney()-money);
        account2.setMoney(account2.getMoney()-money);
        accountDao.updateAccount(account1);
        accountDao.updateAccount(account2);
      }
}
