package com.atguigu.java;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;
import sun.security.provider.Sun;

import java.net.URL;

public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println("启动引导类加载器");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url);
        }

        //引导类加载器
        ClassLoader classLoader = Sun.class.getClassLoader();
        System.out.println(classLoader);//null

        System.out.println("启动扩展类加载器");
        String property = System.getProperty("java.ext.dirs");
        for(String path : property.split(";")){
            System.out.println(path);
        }

        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@6e0be858
    }
}
