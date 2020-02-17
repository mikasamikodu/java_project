package com.atguigu.springboot;

import com.atguigu.springboot.log.MyLogger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02LoggingApplicationTests {

    @Autowired
    private MyLogger myLogger;

    @Test
    void contextLoads() {
        myLogger.contextLoads();
    }
}
