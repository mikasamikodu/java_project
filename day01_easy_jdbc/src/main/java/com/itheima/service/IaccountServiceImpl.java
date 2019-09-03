package com.itheima.service;

import com.itheima.dao.IaccountDao;
import com.itheima.dao.IaccountDaoImpl;

import java.util.Date;

public class IaccountServiceImpl implements IaccountService {

    /*
    * <bean id="IaccountService" class="com.itheima.service.IaccountServiceImpl"
    *       scrope="" init-method="" destroy-method="" >
    *       <property name=""value=""| ref=""></property>
    * </bean>
    *   注解按作用分四类：
    *       用于创建对象：作用与<bean>标签相同
    *       用于注入数据：作用与<property>标签相同
    *       用于改变生命周期：作用与scrope属性相同
    *       和生命周期相关：作用与init-method,destroy-method相同
    * */
    private String name;
    private Integer age;
    private Date birth;

    public IaccountServiceImpl(String name,Integer age,Date birth){
        this.name = name;
        this.age = age;
        this.birth = birth;
//        System.out.println("对象创建了");
    }

    public void saveAccount(){
//        dao.saveAccount();
        System.out.println("IaccountServiceImpl方法执行了！"+name+","+age+","+birth);
    }
}
