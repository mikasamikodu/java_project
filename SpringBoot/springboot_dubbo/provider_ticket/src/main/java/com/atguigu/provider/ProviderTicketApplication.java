package com.atguigu.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1.将服务提供者注册到注册中心
		1.引入依赖（dubbo和zookeeper的依赖）
			<dependency>
				<groupId>com.alibaba.boot</groupId>
				<artifactId>dubbo-spring-boot-starter</artifactId>
				<version>0.1.0</version>
			</dependency>

			<!--引入zookeeper的客户端工具-->
			<dependency>
				<groupId>com.github.sgroschupf</groupId>
				<artifactId>zkclient</artifactId>
				<version>0.1</version>
			</dependency>

		2.对dubbo进行配置
			dubbo.application.name=provider_ticket
			dubbo.registry.address=zookeeper://192.168.1.106:2181
			dubbo.scan.base-packages=com.atguigu.provider.service.impl
		3.在需要发布出去的方法所在类上标注@Service(dubbo的注解)
* */
@SpringBootApplication
public class ProviderTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderTicketApplication.class, args);
	}

}
