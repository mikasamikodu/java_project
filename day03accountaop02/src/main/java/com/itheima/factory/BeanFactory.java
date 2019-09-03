package com.itheima.factory;

import com.itheima.domain.IAccount;
import com.itheima.service.IAccountService;
import com.itheima.util.TransactionManager;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
@Component("beanFactory")
@Aspect
public class BeanFactory {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private TransactionManager transactionManager;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

    @Around("pt1()")
    public IAccountService getAccountService(){
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object value = null;
                        try{
                            //1.开启事务
                            transactionManager.beginTransaction();
                            //2.进行操作
                            System.out.println("前置");
                            value = method.invoke(accountService, args );
                            //3.提交事务
                            System.out.println("后置");
                            transactionManager.commit();
                            //4.返回结果
                            return value;
                        }catch(Exception e){
                            //5.回滚事务
                            System.out.println("异常");
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        }finally{
                            //6.关闭连接
                            System.out.println("最终");
                            transactionManager.release();
                        }
                    }
                });
    }
}
