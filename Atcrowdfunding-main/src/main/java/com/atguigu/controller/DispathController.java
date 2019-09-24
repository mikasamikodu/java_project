package com.atguigu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.User;
import com.atguigu.manager.service.UserService;
import com.atguigu.utils.Const;

@Controller
public class DispathController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd,String type,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginacct", loginacct);
		map.put("userpswd", userpswd);
		map.put("type", type);
		User user = userService.queryUserLogin(map);
		session.setAttribute(Const.LOGIN_USER, user);
		return "redirect:/main.htm";
	}
	
	
	@RequestMapping("/reg")
	public String register() {
		return "reg";
	}
	
	@RequestMapping("/project")
	public String project() {
		return "project";
	}
	
	@RequestMapping("/accttype")
	public String accttype() {
		return "accttype";
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	
	@RequestMapping("/minecrowdfunding")
	public String minecrowdfunding() {
		return "minecrowdfunding";
	}
}
