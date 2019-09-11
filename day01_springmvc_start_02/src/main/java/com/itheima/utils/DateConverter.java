package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if(s==null){
            throw new RuntimeException("请输入数据！！！");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(s);
        }catch(Exception e){
            throw new RuntimeException("数据类型转换异常！！");
        }
    }
}
