package com.atguigu.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    rabbitmq的自动配置原理
    1.自动配置类RabbitAutoConfiguration
    2.有自动配置的连接工厂ConnectionFactory；
    3.RabbitmqProperties封装了Rabbitmq的配置
    4.RabbitTemplate;给rabbitmq发送和接受消息
    5.AmqpAdmin:RabbitMQ系统功能管理组件
    6.@EnableRabbit+@RabbitListener开启对消息队列的监听
* */
@EnableRabbit//开启rabbitmq相关注解的功能
@SpringBootApplication
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }

}
