package com.itheima.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("iaccountDao2")
public class IaccountDaoImpl2 implements IaccountDao {

    public void saveAccount(){
//        try {
//            DBUtils.getConntion();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        System.out.println("执行了dao的saveAccount2方法");
    }
}
