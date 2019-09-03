package com.itheima.service;

import com.itheima.domain.IAccount;

import java.util.List;

/*
业务层
* */
public interface IAccountService {

    public IAccount findById(int id);

    public void transfer(String source,String targer,float money);
}
