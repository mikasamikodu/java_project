package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert")
public class CertController {

	@RequestMapping("/index")
	public String param() {
		return "cert/index";
	}	  
}
