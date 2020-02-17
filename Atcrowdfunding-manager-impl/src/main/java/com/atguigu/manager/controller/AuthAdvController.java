package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authadv")
public class AuthAdvController {

	@RequestMapping("/index")
	public String param() {
		return "authadv/index";
	}	  
}
