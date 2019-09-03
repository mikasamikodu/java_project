package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect//表示这是一个切面类
public class Logger {
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}
    /**
     * 前置通知：用于打印日志，计划让在执行点方法执行前执行
     */
//    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("logger类的befoerPrintLog方法开始执行了。");
    }


    /**
     * 后置通知：用于打印日志，计划让在执行点方法执行后执行
     */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog(){
        System.out.println("logger类的afterReturningPrintLog方法开始执行了。");
    }

    /**
     * 异常通知：用于打印日志，计划让在执行点方法执行出异常时执行
     */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("logger类的afterThrowingPrintLog方法开始执行了。");
    }

    /**
     * 最终通知：用于打印日志，计划让在执行点方法执行完执行
     */
//    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("logger类的afterPrintLog方法开始执行了。");
    }

    /**
     * 环绕通知：它是spring为我们提供的一种可以在代码中手动控制增强方法执行的方式
     *  问题：当配置了环绕通知时，切入点方法未执行，二环绕通知却执行了
     *  分析：通过对比动态代理的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，
     *        而我们的代码中没有
     *  解决：spring为我们提供了一个接口ProceedingJoinPoint。该接口有一个方法proceed()。
     *        此方法就相当于明确调用切入点方法，该接口可以作为环绕通知的方法参数，在程序
     *        执行时spring会为我们提供该接口的实现类使用
     *
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint point){
        Object  value = null;
        try{
            Object[] obj = point.getArgs();//得到切入点方法执行参数
            System.out.println("logger类的aroundPrintLog方法开始执行了,前置");
            value = point.proceed(obj);
            System.out.println("logger类的aroundPrintLog方法开始执行了,后置");
            return value;
        }catch(Throwable e){
            System.out.println("logger类的aroundPrintLog方法开始执行了,异常");
            throw new RuntimeException(e);
        }finally{
            System.out.println("logger类的aroundPrintLog方法开始执行了,最终");
        }
    }
}
