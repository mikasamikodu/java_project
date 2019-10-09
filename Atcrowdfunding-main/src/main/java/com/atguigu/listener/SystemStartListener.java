package com.atguigu.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.bean.Permission;
import com.atguigu.manager.service.PermissionService;
import com.atguigu.utils.Const;

public class SystemStartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("APP_PATH", path);
		System.out.println("SystemStartListener监听器将上下文放入application域中了。。。");
		
		ApplicationContext app = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		PermissionService permissionService = app.getBean(PermissionService.class);
		List<Permission> permissions = permissionService.queryAllPermission();
		Set<String> permissionUriSet = new HashSet<String>();
		for(Permission permission:permissions) {
			permissionUriSet.add("/"+permission.getUrl());
		}
		context.setAttribute(Const.ALL_PERMISSION_URIS, permissionUriSet);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

}
