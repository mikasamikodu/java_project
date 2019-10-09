package com.atguigu.interceptor;

//import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.atguigu.bean.Permission;
//import com.atguigu.manager.service.PermissionService;
import com.atguigu.utils.Const;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	//@Autowired
	//private PermissionService permissionService; 
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		List<Permission> permissions = permissionService.queryAllPermission();
//		Set<String> permissionUriSet = new HashSet<String>();
//		for(Permission permission:permissions) {
//			permissionUriSet.add("/"+permission.getUrl());
//		}
		Set<String> permissionUriSet = (Set<String>) request.getSession().getServletContext().getAttribute(Const.ALL_PERMISSION_URIS);
		String requestPath = request.getServletPath();
		if(permissionUriSet.contains(requestPath)) {
			Set<String> myUris = (Set<String>)request.getSession().getAttribute(Const.MY_URIS);
			if(myUris.contains(requestPath)) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/login.htm");
				return false;
			}
		}else {
			return true;
		}
	}

}
