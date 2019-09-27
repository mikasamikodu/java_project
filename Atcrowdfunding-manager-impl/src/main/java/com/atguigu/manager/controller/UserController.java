package com.atguigu.manager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.manager.service.UserService;
import com.atguigu.utils.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(@RequestParam(value="pageNo",required=false,defaultValue="1") Integer pageNo,
						@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
						Map<String, Page> map) {
		Page page = userService.queryPage(pageNo, pageSize);
		map.put("page", page);
		return "user/index";
	}
}
