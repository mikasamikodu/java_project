package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authproject")
public class AuthProjectController {

	@RequestMapping("/index")
	public String param() {
		return "authproject/index";
	}	  
}
