package com.itheima.service;

import com.itheima.dao.IaccountDao;
import com.itheima.dao.IaccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;
@Component(value = "IaccountService")
//@Scope(value="prototype")
public class IaccountServiceImpl implements IaccountService {

    /*
    * <bean id="IaccountService" class="com.itheima.service.IaccountServiceImpl"
    *       scrope="" init-method="" destroy-method="" >
    *       <property name=""value=""| ref=""></property>
    * </bean>
    *   注解按作用分四类：
    *       1.用于创建对象：
    *           作用与<bean>标签相同
    *           @Component作用：把当前类装进spring容器中
    *           属性：value，作用是用于指定bean的id，默认是当前类名，并且首字母小写，如iaccountServiceImpl
    *           @Controller:一般用于表现层
    *           @Service:一般用于业务层
    *           @Repository:一般用于持久层
    *           以上三个注解与@Component作用属性相同。目的是为了使我们对三层对象能区别对待
    *       2.用于注入数据：作用与<property>标签相同
    *           @Autowired作用：自动按照类型注入。只要容器中有唯一的bean对象类型和要注入的变量类型匹配，就可注入成功
    *                           如果ioc容器中没有任何bean类型和要注入的变量类型，就会报错
    *                           如果容器中有多个类型匹配时,spring会首先用数据类型（如IaccountDao）缩小范围，只查找
    *                           实现了IaccountDao接口的类，然后根据@Autowired注解的类的名称(如dao)，
    *                           再在上述范围内查找与名称相同的类名称，有就用这个名称相同的类，没有就报错
     *          出现位置：可以是变量，也可以是方法；
    *           注意：有了这个注解，set，方法就不是必须的了。
    *           @Qualifier作用: 在按照类中注入的基础上再按照名称注入。它咋给类中成员变量注入时不能单独使用，但在给方法注入时可以单独使用。
    *                     属性：value--指定注入的值
    *           由于上面两个注解需要联合使用，麻烦，因此有一个注解可实现上述两个注解的功能，即@Resource
    *           @Resource作用：直接按照bean的id注入，可单独使用
    *                    属性：name--用于指定bean的id
    *            以上三个注解只能注入其它bean类型的数据，而基本数据类型与String类型不能使用上述注解注入、
    *            另外集合类型的数据只能通过xml注入
    *            @Value作用：用于注入基本类型与spring类型的数据
    *                   属性：value--用于指定数据的值，也可以使用spring的El表达式
    *                   spring的EL表达式：${表达式}
    *       3.用于改变生命周期：作用与scrope属性相同
    *       @Scope作用:用于指定bean的作用范围
    *             属性：value--指定范围的值，常用取值：singleton(单例) prototype（多例）
    *       4.和生命周期相关：作用与init-method,destroy-method相同(掌握程度：了解)
    *       @PreDestroy作用：用于指定销毁方法
    *       @PostConstruct作用：用于指定初始化方法
    * */
//    @Autowired
//    @Qualifier("iaccountDao2")
    @Resource(name="iaccountDao2")
    private IaccountDao dao = null;
//    public IaccountServiceImpl(){
//        System.out.println("对象创建了");
//    }
    @PostConstruct
    public void init(){
        System.out.println("IaccountServiceImpl方法初始化了！");
    }
    @PreDestroy
    public void destory(){
        System.out.println("IaccountServiceImpl方法销毁了！");
    }

    public void saveAccount(){
        dao.saveAccount();
//        System.out.println("IaccountServiceImpl方法执行了！");
    }
}
