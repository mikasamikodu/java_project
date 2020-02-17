package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "hello world!");
        return "success";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if("aaa".equals(user)){
            throw new UserNotExistException();
        }
        return "success";
    }

//    @RequestMapping({"/", "/index.html"})
//    public String login(){
//        return "login";
//    }
}
