package com.atguigu.potal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/index")
	public String index() {
		return "member/index";
	}
	
	@RequestMapping("/accttype")
	public String accttype() {
		return "member/accttype";
	}
	
	@RequestMapping("/apply")
	public String apply() {
		return "member/apply";
	}
	
	
}
