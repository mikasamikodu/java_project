package com.itheima.controller;

import com.itheima.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testException")
    public String testException() throws SysException{
        System.out.println("testException");
		try{
			int a = 10/0;
        }catch(Exception e){
			e.printStackTrace();
			throw new SysException("查询用户失败！！");
		}
		return "success";
    }
    @RequestMapping("/testIntercepter")
    public String testIntercepter(){
        System.out.println("testIntercepter");
		return "success";
    }
}
