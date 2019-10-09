package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project_type")
public class ProjectTypeController {

	@RequestMapping("/index")
	public String param() {
		return "project_type/index";
	}	  
}
