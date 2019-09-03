package com.itheima.dao;

import com.itheima.util.DBUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("iaccountDao1")
public class IaccountDaoImpl implements IaccountDao {

    public void saveAccount(){
//        try {
//            DBUtils.getConntion();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        System.out.println("执行了dao的saveAccount1方法");
    }
}
