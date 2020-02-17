package com.atguigu.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.provider.service.TickerService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference
    TickerService tickerService;

    public void hello(){
        String ticket = tickerService.getTicket();
        System.out.println(ticket);
    }
}
