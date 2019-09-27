package com.atguigu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Member;
import com.atguigu.bean.User;
import com.atguigu.manager.service.UserService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Const;
import com.atguigu.utils.MD5Util;

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
	
	/*Í¬²½ÇëÇó
	 * @RequestMapping("/doLogin") public String doLogin(String loginacct,String
	 * userpswd,String type,HttpSession session) { Map<String,Object> map = new
	 * HashMap<String,Object>(); map.put("loginacct", loginacct);
	 * map.put("userpswd", userpswd); map.put("type", type); User user =
	 * userService.queryUserLogin(map); session.setAttribute(Const.LOGIN_USER,
	 * user); return "redirect:/main.htm"; }
	 */
	//Òì²½ÇëÇó
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(String loginacct,String userpswd,String type,HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginacct", loginacct);
			map.put("userpswd", MD5Util.digest(userpswd));
			map.put("type", type);
			User user = userService.queryUserLogin(map);
			session.setAttribute(Const.LOGIN_USER, user);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setMessage("µÇÂ¼Ê§°Ü£¡");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.htm";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/doRegister")
	public AjaxResult doRegister(Member member) {
		AjaxResult result = new AjaxResult();
		try {
			member.setUsername(member.getLoginacct());
			member.setAuthstatus("0");
			member.setUserpswd(MD5Util.digest(member.getUserpswd()));
			userService.doRegister(member);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setMessage("×¢²áÊ§°Ü£¡");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
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
