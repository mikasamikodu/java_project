package com.itheima.service;

import java.util.Date;

public class IaccountServiceImpl2 implements IaccountService {

    private String name;
    private Integer age;
    private Date birth;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void saveAccount(){
//        dao.saveAccount();
        System.out.println("IaccountServiceImpl方法执行了！"+name+","+age+","+birth);
    }
}
