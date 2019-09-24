package com.atguigu.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.manager.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
}
