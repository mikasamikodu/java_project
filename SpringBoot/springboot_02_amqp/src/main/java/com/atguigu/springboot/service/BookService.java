package com.atguigu.springboot.service;

import com.atguigu.springboot.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public void  get(Book book){
        System.out.println("收到的消息："+ book);
    }
}
