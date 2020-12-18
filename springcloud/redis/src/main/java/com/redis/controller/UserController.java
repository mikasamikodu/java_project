package com.redis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login")
	public String login(@RequestParam(name="username") final String username,
						@RequestParam(name="password") final String password,
						@RequestParam(name="valcode") final String valcode) {
		//1.判断用户是否已被限制登陆，是的话则给予提示。否的话进行登录
		Map<String, Object> map = userService.loginLock(username);
		if((boolean) map.get("flag")) {//已被限制登陆
			//给予提示
			return "该用户"+username+"登录次数超过限制，已被禁止登陆，禁止时间还有"+map.get("time")+"分钟";
		}else {
			//登录
			boolean login = userService.login(username, password);
			//2.登录成功则清空登陆失败次数，登陆失败则进行次数的累计，达到5次就限制其登录
			if(login) {//登录成功
				//清空登陆失败次数
				userService.clearCount(username);
				return "success.jsp";
			}else {//登陆失败,进行次数的累计,同时进行登录失败的提示
				//3.检查是否已有失败的key，没有则设置一个key，然后设置过期时间；
				//如果有就检查key的值是否小于4.是的话就值加1，否的话就对登录做出限制，然后设置过期时间
				String result = userService.loginFail(username);
				return result;
			}
		}
	}
}
