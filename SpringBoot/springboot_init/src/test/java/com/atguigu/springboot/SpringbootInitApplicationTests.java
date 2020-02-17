package com.atguigu.springboot;

import com.atguigu.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

//@Runwith(SpringRunner.class)
@SpringBootTest
class SpringbootInitApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test1(){
        System.out.println(applicationContext.containsBean("helloService"));
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
