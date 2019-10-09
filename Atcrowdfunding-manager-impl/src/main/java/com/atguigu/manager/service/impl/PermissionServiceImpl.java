package com.atguigu.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Permission;
import com.atguigu.manager.dao.PermissionMapper;
import com.atguigu.manager.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> queryByPid(Integer pid) {
		return permissionMapper.queryByPid(pid);
	}

	@Override
	public Permission getRootPermission() {
		int id = 1;
		return permissionMapper.getRootPermission(id);
	}

	@Override
	public List<Permission> queryAllPermission() {
		return permissionMapper.selectAll();
	}

	@Override
	public int savePermission(Permission permission) {
		return permissionMapper.insert(permission);
	}

	@Override
	public int editPermission(Permission permission) {
		return permissionMapper.updateByPrimaryKey(permission);
	}

	@Override
	public Permission queryPermissionById(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deletePermission(Integer id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}
}
