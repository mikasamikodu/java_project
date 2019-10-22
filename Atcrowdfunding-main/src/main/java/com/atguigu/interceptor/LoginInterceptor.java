package com.atguigu.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.bean.Member;
import com.atguigu.bean.User;
import com.atguigu.utils.Const;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Set<String> uri = new HashSet<String>();
		uri.add("/register.htm");
		uri.add("/login.htm");
		uri.add("/doRegister.do");
		uri.add("/doLogin.do");
		uri.add("/logout.do");
		uri.add("/index.htm");
		
		String servletPath = request.getServletPath();
		if(uri.contains(servletPath)) {
			return true;
		}else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute(Const.LOGIN_USER);
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			if(user!=null || member!=null) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/login.htm");
				return false;
			}
		}
	}
}
