package com.itheima.dao;

import com.itheima.util.DBUtils;

public class IaccountDaoImpl implements IaccountDao {

    public void saveAccount(){
        try {
            DBUtils.getConntion();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
