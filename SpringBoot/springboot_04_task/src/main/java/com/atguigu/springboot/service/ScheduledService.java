package com.atguigu.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0 * * * * 1-6")
    public void hello(){
        System.out.println("hello----");
    }
}
