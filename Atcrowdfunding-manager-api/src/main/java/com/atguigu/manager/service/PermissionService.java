package com.atguigu.manager.service;

import java.util.List;

import com.atguigu.bean.Permission;

public interface PermissionService {

	List<Permission> queryByPid(Integer pid);

	Permission getRootPermission();

	List<Permission> queryAllPermission();

	int savePermission(Permission permission);

	int editPermission(Permission permission);

	Permission queryPermissionById(Integer id);

	int deletePermission(Integer id);

}
