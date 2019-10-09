package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class ParamController {

	@RequestMapping("/index")
	public String param() {
		return "param/index";
	}	  
}
