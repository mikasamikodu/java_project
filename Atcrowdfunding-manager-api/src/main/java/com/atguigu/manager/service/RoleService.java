package com.atguigu.manager.service;

import java.util.Map;

import com.atguigu.utils.Page;

public interface RoleService {

	Page queryPage(Map<String, Object> map);

	Integer deleteRole(Integer id);

	Integer delBatch(Integer[] id);

}
