package com.atguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
  一.搭建基本环境
    1.准备数据库，导入数据库文件，创建department表和employee表
    2.创建javaBean封装数据
    3.整合mybatis操作数据库
        1.配置数据源信息
        2.使用注解版的mybatis
            1.使用@MapperScan指定需要扫描的mapper接口所在的包
  二.快速体验缓存
    1.开启基于注解的缓存
        @EnableCaching
    2.标注缓存注解即可
        @Cacheable:缓存方法结果
        @CacheEvict:清除缓存
        @CachePut:每次清空缓存并将方法结果放入缓存

   三.整合redis作为缓存
        1.安装redis--使用docker的方式
        2.引入redis的starter
        3.配置redis
        4.测试缓存
            原理是CacheManager==Cache缓存组件来实际给缓存中存取数据
            1.引入redis的starter，容器中保存的是就是RedisCacheManager
            2.RedisCacheManager帮我们创建RedisCache作为缓存组件。RedisCache通过操作redis缓存数据
            3.默认保存的key-value是Object类型，利用序列化保存。
                如何保存为json呢？
                    1.引入redis的starter，CacheManager就会变成RedisCacheManager；
                    2.默认创建的RedisCacheManager操作redis的时候使用的是RedisTemplate<Object,Object>
                    3.RedisTemplate<Object,Object>是默认使用jdk的序列化机制
            4.自定义CacheManager
  * */

@MapperScan("com.atguigu.springboot.mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootCache01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCache01Application.class, args);
    }

}
