package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.util.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory2 {

    private IAccountService accountService;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

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
                            value = method.invoke(accountService, args );
                            //3.提交事务
                            transactionManager.commit();
                            //4.返回结果
                            return value;
                        }catch(Exception e){
                            //5.回滚事务
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        }finally{
                            //6.关闭连接
                            transactionManager.release();
                        }
                    }
                });
    }
}
