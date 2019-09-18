package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		System.out.println("interceptor执行了11--前面");
		return true;
	}
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView view) throws Exception{
		System.out.println("interceptor执行了11--后面");
	}
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception e) throws Exception{
		System.out.println("interceptor执行了11--最后");
	}
}