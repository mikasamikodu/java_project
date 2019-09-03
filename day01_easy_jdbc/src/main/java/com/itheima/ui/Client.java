package com.itheima.ui;

import com.itheima.service.IaccountService;
import com.itheima.service.IaccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args){
        //ApplicationContext创建对象采用的策略是立即加载，即加载完配置文件就创建对象并存入容器，实际采用这种方式的更对，
        // 相对于BeanFactory的延迟加载（加载完配置文件并不会创建对象，而是使用时才会创建对象）
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        IaccountService service = app.getBean("IaccountService3", IaccountService.class);
//        IaccountService service = app.getBean("IaccountService", IaccountService.class);
//        IaccountService service = new IaccountServiceImpl();
        service.saveAccount();
    }


}
