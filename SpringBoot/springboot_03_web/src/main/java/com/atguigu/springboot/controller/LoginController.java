package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(StringUtils.isEmpty(username)){
            map.put("errorMsg_username", "用户名不能为空");
            return "login";
        }else if(!(("123456").equals(password))){
            map.put("errorMsg_password", "密码错误");
            return "login";
        }else{
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
    }
}
