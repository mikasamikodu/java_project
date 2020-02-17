package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//springmvc中的知识
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)//异常处理器,需指定要处理的异常
    public String exceptionHandler(Exception e, HttpServletRequest request){
        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        request.setAttribute("extend", map);
        return "forward:/error";
    }
/*
    @ResponseBody//返回数据
    @ExceptionHandler(UserNotExistException.class)//异常处理器,需指定要处理的异常
    public Map<String, Object> exceptionHandler(Exception e){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }
*/
}
