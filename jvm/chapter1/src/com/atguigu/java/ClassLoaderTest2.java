package com.atguigu.java;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;
import sun.security.provider.Sun;

import java.net.URL;

public class ClassLoaderTest2 {
    public static void main(String[] args) {
        try {
            //获取指定类的类加载器
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);//null

            //获取当前线程的类加载器
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(contextClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

            //获取系统的类加载器
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
