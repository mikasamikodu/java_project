package com.itheima.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/*
*该类是一个配置类，作用于bean.xml一样
* spring注解
* Configuration作用：指定当前类为配置类
*              细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，这个注解可以不用
* ComponentScan作用:通过注解指定spring容器创建时需要扫描的包
*              属性：value--它和basepackage作用一样，即指定需要扫描的包，
*              作用相当于xml配置的
*               <context:component-scan base-package="com.itheima"></context:component-scan>
* Bean作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
*     属性：name--用于指定bean的id,默认值为当前方法的名称
* 细节：当使用注解配置方法时，如果方法内有参数，则spring会去容器中找是否有合适的bean对象
*       查找方式与Autowired注解作用一致
* Import作用：导入其他配置类
* PropertySource作用：用于指定properties文件位置
*               属性：value--指定文件的名称与路径
*                       关键字classpath,表示类路径下
* */
//@Configuration
@ComponentScan("com.itheima")
@Import(JDBCConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
@EnableAspectJAutoProxy
public class SpringConfiguration {


}
