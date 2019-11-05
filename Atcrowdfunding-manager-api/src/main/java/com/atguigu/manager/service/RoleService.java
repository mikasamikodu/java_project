package com.atguigu.manager.service;

import java.util.Map;

import com.atguigu.bean.Role;
import com.atguigu.utils.Page;

public interface RoleService {

	Page queryPage(Map<String, Object> map);

	Integer deleteRole(Integer id);

	Integer delBatch(Integer[] id);

	int saveRole(Role role);

	int updateRole(Role role);

	Role selectRoleById(Integer id);

}
