package com.atguigu.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.atguigu.provider.service.TickerService;
import org.springframework.stereotype.Component;

@EnableDubbo
@Service    //将服务发布出去
@Component
public class TickerServiceImpl implements TickerService {

    @Override
    public String getTicket() {
        return "《从零开始》";
    }
}
