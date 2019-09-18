package com.itheima.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver{
	
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		SysException e = null;
		if(ex instanceof SysException){
			e = (SysException)ex;
		}else{
			e = new SysException("系统正在维护！！");
		}
		ModelAndView view = new ModelAndView();
		view.addObject("errormsg", e.getMessage());
		view.setViewName("error");
		return view;
	}
}