package com.atguigu.springboot_web_04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/mm")
    public String hello(Model model){
        model.addAttribute("aa", "你好");
        return "success";
    }
}
