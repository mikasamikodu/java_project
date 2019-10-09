package com.atguigu.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.manager.dao.RolePermissionMapper;
import com.atguigu.manager.service.RolePermissionService;
import com.atguigu.vo.Data;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<Integer> queryById(Integer roleid) {
		return rolePermissionMapper.selectByPrimaryKey(roleid);
	}

	@Override
	public int saveAll(Integer roleid, Data data) {
		rolePermissionMapper.deleteByRoleid(roleid);
		int count = 0 ;
		for(Integer permissionid:data.getIds()) {
			count += rolePermissionMapper.insertByid(roleid, permissionid);
		}
		return count;
	}
}
