package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args){
        final IProducer producer = new Producer();

        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上，对方法增强
         *  分类：1.基于接口的动态代理；2.基于子类的动态代理
         *  本次使用基于接口的动态代理
         *  基于接口的动态代理：
         *      涉及的类：Proxy
         *      提供者：JDK官方
         *   如何创建代理对象：使用Proxy的newProxyInstance方法
         *   创建要求：被代理对象至少有一个接口
         *   Proxy的newProxyInstance方法参数：
         *      classLoader:
         *          类加载器，用于加载代理对象的字节码，和被代理对象使用相同的类加载器，
         *          固定写法,被代理对象.getClass().getClassLoader()
         *      class[]:字节码数组
         *          让代理对象与被代理对象有相同的方法，
         *          固定写法: 被代理对象..getClass().getInterfaces()
         *       InvocationHandler：用于提供增强的代码
         *          它是让我们写如何代理，我们一般写一个接口的实现类，通常是一个匿名内部类，但不是必须的
         *          接口的实现类是谁用谁写
         */
        IProducer proxyProducer = (IProducer)Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任意接口与方法时都会记过该方法
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /**
                         * 此处需要用到producer这个被代理的对象，因为实在匿名内部类用，所以需要将该变量的声明变为final
                         * proxy：被代理对象
                         * method:被代理对象的方法
                         * args:被代理对象的方法的方法参数
                         */
                        Object value = null;
                        //获取方法参数
                        float money = (Float)args[0];
                        //判断是否是销售，是的话就进行处理
                        if("saleProduct".equals(method.getName())){
                            value = method.invoke(producer, money*0.8f);
                        }
                        return value;
                    }
                });
        proxyProducer.saleProduct(1000f);
    }
}
