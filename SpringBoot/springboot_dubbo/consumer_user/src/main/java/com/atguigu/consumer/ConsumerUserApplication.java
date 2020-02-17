package com.atguigu.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1.将服务提供者注册到注册中心
		1.引入依赖（dubbo和zookeeper的依赖）
		2.对dubbo进行配置
		3.在需要接受的方法所在类上标注注解
* */
@SpringBootApplication
public class ConsumerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerUserApplication.class, args);
	}

}
