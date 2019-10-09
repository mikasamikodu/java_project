package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth_project")
public class AuthProjectController {

	@RequestMapping("/index")
	public String param() {
		return "auth_project/index";
	}	  
}
