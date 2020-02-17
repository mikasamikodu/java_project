package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath:/bean.xml")
@SpringBootApplication
public class SpringbootInitApplication {

    public static void main(String[] args   ) {
        SpringApplication.run(SpringbootInitApplication.class, args);
    }

}
