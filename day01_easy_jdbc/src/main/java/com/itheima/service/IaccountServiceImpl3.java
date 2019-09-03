package com.itheima.service;
import java.util.*;

public class IaccountServiceImpl3 implements IaccountService {

    private String[] str;
    private List<String> list;
    private Map<String, Object> map;
    private Properties property;
    private Set<String> set;

    public void setStr(String[] str) {
        this.str = str;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void saveAccount(){
//        dao.saveAccount();
        System.out.println(Arrays.toString(str));
        System.out.println(map);
        System.out.println(list);
        System.out.println(property);
        System.out.println(set);
    }
}
