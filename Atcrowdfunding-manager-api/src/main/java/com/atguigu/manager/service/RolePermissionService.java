package com.atguigu.manager.service;

import java.util.List;

import com.atguigu.vo.Data;

public interface RolePermissionService {

	List<Integer> queryById(Integer roleid);

	int saveAll(Integer roleid, Data data);

}
