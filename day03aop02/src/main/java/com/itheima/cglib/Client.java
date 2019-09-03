package com.itheima.cglib;

import com.itheima.proxy.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args){
        final Producer producer = new Producer();

        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上，对方法增强
         *  分类：1.基于接口的动态代理；2.基于子类的动态代理
         *  本次使用基于子类的动态代理
         *  基于子类的动态代理：
         *      涉及的类：Enhancer
         *      提供者：第三方库cglib
         *   如何创建代理对象：使用Enhancer的create方法
         *   创建要求：被代理对象不能是最终类(被final修饰的类)
         *   Enhancer的create方法参数：
         *      class:
         *          用于指定代理对象的字节码
         *          固定写法,被代理对象.getClass()
         *      callback:
         *         它是让我们写如何代理，我们一般写一个接口的实现类，通常是一个匿名内部类，但不是必须的
         *          接口的实现类是谁用谁写
         *          一般写该接口的子接口实现类：MethodInterceptor
         */
        Producer cglibProducer = (Producer)Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任意接口与方法时都会记过该方法
             * @param o
             * @param method
             * @param objects
             * 以上三个参数和基于接口实现的动态代理中的三个参数相同
             * @param methodProxy： 代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object value = null;
                //获取方法参数
                float money = (Float)objects[0];
                //判断是否是销售，是的话就进行处理
                if("saleProduct".equals(method.getName())){
                    value = method.invoke(producer, money*0.8f);
                }
                return value;
            }
        });
        cglibProducer.saleProduct(1000f);
    }
}
