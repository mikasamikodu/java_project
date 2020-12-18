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
						@RequestParam(name="password",required=false)final String password,
						@RequestParam(name="valcode",required=false)final String valcode) {
		//1.锟叫讹拷锟矫伙拷锟角凤拷锟窖憋拷锟斤拷锟狡碉拷陆锟斤拷锟角的伙拷锟斤拷锟斤拷锟斤拷锟绞撅拷锟斤拷锟侥伙拷锟斤拷锟叫碉拷录
		Map<String, Object> map = userService.loginLock(username);
		if((boolean) map.get("flag")) {//锟窖憋拷锟斤拷锟狡碉拷陆
			//锟斤拷锟斤拷锟斤拷示
			return "锟斤拷锟矫伙拷"+username+"锟斤拷录锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟狡ｏ拷锟窖憋拷锟斤拷止锟斤拷陆锟斤拷锟斤拷止时锟戒还锟斤拷"+map.get("time")+"锟斤拷锟斤拷";
		}else {
			//锟斤拷录
			boolean login = userService.login(username, password);
			//2.锟斤拷录锟缴癸拷锟斤拷锟斤拷盏锟铰绞э拷艽锟斤拷锟斤拷锟斤拷锟铰绞э拷锟斤拷锟斤拷锟叫达拷锟斤拷锟斤拷锟桔计ｏ拷锟斤到5锟轿撅拷锟斤拷锟斤拷锟斤拷锟铰�
			if(login) {//锟斤拷录锟缴癸拷
				//锟斤拷盏锟铰绞э拷艽锟斤拷锟�
				userService.clearCount(username);
				return "/success.jsp";
			}else {//锟斤拷陆失锟斤拷,锟斤拷锟叫达拷锟斤拷锟斤拷锟桔硷拷,同时锟斤拷锟叫碉拷录失锟杰碉拷锟斤拷示
				//3.锟斤拷锟斤拷欠锟斤拷锟斤拷锟绞э拷艿锟絢ey锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷key锟斤拷然锟斤拷锟斤拷锟矫癸拷锟斤拷时锟戒；
				//锟斤拷锟斤拷芯图锟斤拷key锟斤拷值锟角凤拷小锟斤拷4.锟角的伙拷锟斤拷值锟斤拷1锟斤拷锟斤拷幕锟斤拷投缘锟铰硷拷锟斤拷锟斤拷锟斤拷疲锟饺伙拷锟斤拷锟斤拷霉锟斤拷锟绞憋拷锟�
				String result = userService.loginFail(username);
				return result;
			}
		}
	}
}
