package com.atguigu.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Member;
import com.atguigu.bean.Permission;
import com.atguigu.bean.User;
import com.atguigu.manager.service.UserService;
import com.atguigu.potal.service.MemberService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Const;
import com.atguigu.utils.MD5Util;

@Controller
public class DispathController {

	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session) {
		
		boolean needLogin = true;
		String logintype = null;
		Cookie[] cookies = request.getCookies();
		Map<String,Object> map = new HashMap<String,Object>();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if("loginCode".equals(cookie.getName())) {
					String loginCode = cookie.getValue();
					String[] split = loginCode.split("&");
					if(split.length==3) {
						String loginacct = split[0].split("=")[1];
						String userpwd = split[1].split("=")[1];
						logintype = split[2].split("=")[1];
						map.put("loginacct", loginacct);
						map.put("userpswd", userpwd);
						if("member".equals(logintype)) {
							Member member = memberService.queryMemberLogin(map);
							if(member!=null) {
								session.setAttribute(Const.LOGIN_MEMBER, member);
								needLogin = false;
							}
						}else if("user".equals(logintype)) {
							User user = userService.queryUserLogin(map);
							if(user!=null) {
								userLogin(user,session);
								needLogin = false;
								
							}
						}
					}
				}
			}
		}
		
		if(!needLogin) {
			if("member".equals(logintype)) {
				return "redirect:member/index.htm";
			}else if("user".equals(logintype)) {
				return "redirect:main.htm";
			}
		}
		return "login";
	}
	
	/*同步请求
	 * @RequestMapping("/doLogin") public String doLogin(String loginacct,String
	 * userpswd,String type,HttpSession session) { Map<String,Object> map = new
	 * HashMap<String,Object>(); map.put("loginacct", loginacct);
	 * map.put("userpswd", userpswd); map.put("type", type); User user =
	 * userService.queryUserLogin(map); session.setAttribute(Const.LOGIN_USER,
	 * user); return "redirect:/main.htm"; }
	 */
	//异步请求
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(String loginacct,String userpswd,String type,String rememberme,
						  HttpSession session,HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginacct", loginacct);
			map.put("userpswd", MD5Util.digest(userpswd));
			//map.put("type", type);
			
			if("member".equals(type)) {
				Member member = memberService.queryMemberLogin(map);
				session.setAttribute(Const.LOGIN_MEMBER, member);
				
				if("1".equals(rememberme)) {
					String loginCode = "loginacct="+member.getLoginacct()
									 +"&userpwd="+member.getUserpswd()+"&logintype=member";
					Cookie cookie = new Cookie("loginCode", loginCode);
					cookie.setMaxAge(60*60*24*14);//设置cookie有效期两周
					cookie.setPath("/");//设置任意路径下访问都可以访问cookie
					
					response.addCookie(cookie);
				}
			}else if("user".equals(type)) {
				User user = userService.queryUserLogin(map);
				
				userLogin(user,session);
				if("1".equals(rememberme)) {
					String loginCode = "loginacct="+user.getLoginacct()
									 +"&userpwd="+user.getUserpswd()+"&logintype=user";
					Cookie cookie = new Cookie("loginCode", loginCode);
					cookie.setMaxAge(60*60*24*14);//设置cookie有效期两周
					cookie.setPath("/");//设置任意路径下访问都可以访问cookie
					
					response.addCookie(cookie);
				}
			}else {
				throw new Exception();
			}
			result.setSuccess(true);
		}catch(Exception e) {
			result.setMessage("登录失败！");
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
	
	@RequestMapping("/logoff")
	public String logoff(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies) {
			if("loginCode".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return "redirect:/index.htm";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/doRegister")
	public AjaxResult doRegister(Member member) {
		AjaxResult ajax = new AjaxResult();
		try {
			member.setUsername(member.getLoginacct());
			member.setAuthstatus("0");
			member.setUserpswd(MD5Util.digest(member.getUserpswd()));
			int result = memberService.doRegister(member);
			ajax.setSuccess(result==1);
		}catch(Exception e) {
			ajax.setMessage("注册失败！");
			ajax.setSuccess(false);
			e.printStackTrace();
		}
		return ajax;
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
	
	private void userLogin(User user,HttpSession session) {
		List<Permission> permissions = userService.quertPermissionsByUserId(user.getId());
		Map<Integer,Permission> map2 = new HashMap<Integer, Permission>();
		Set<String> permissionUriSet = new HashSet<String>();
		Permission root = null;
		for(Permission permission:permissions) {
			map2.put(permission.getId(), permission);
			permissionUriSet.add("/"+permission.getUrl());
		}
		for(Permission permission:permissions) {
			if(permission.getPid()==null) {
				root = permission;
			}else {
				Permission parent = map2.get(permission.getPid());
				parent.getChildren().add(permission);
			}
		}
		session.setAttribute("permissionRoot", root);
		session.setAttribute(Const.MY_URIS, permissionUriSet);
		
		session.setAttribute(Const.LOGIN_USER, user);
	} 
	
}
