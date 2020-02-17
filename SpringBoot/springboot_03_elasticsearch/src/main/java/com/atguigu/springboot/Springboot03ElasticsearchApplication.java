package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    SpringBoot默认使用两种方式与ES进行交互
       1.Jest(如果需要用这种方式的话就需要导入包io.searchbox.client.JestClient)
       3.Rest(如果需要用这种方式的话就需要导入包org.elasticsearch.client.RestClient)
       2.SpringData ElasticSearch
            1.向容器中注入了一个TransportClient,要注入TransportClient(实现了Client接口)
                就需要在配置文件中配置clusterNodes，clusterName
            2.向容器中注入了一个ElasticsearchRestTemplate用来操作elasticsearch
            3.可以自己编写一个ElasticsearchRepository的实现接口来操作ES
* */
@SpringBootApplication
public class Springboot03ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticsearchApplication.class, args);
    }

}
