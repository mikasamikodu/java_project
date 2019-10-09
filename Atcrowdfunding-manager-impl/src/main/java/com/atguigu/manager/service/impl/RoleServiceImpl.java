package com.atguigu.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Role;
import com.atguigu.manager.dao.RoleMapper;
import com.atguigu.manager.service.RoleService;
import com.atguigu.utils.Page;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	public Page queryPage(Map<String, Object> map) {
		Page page = new Page((Integer)map.get("pageNo"), (Integer)map.get("pageSize"));
		Integer startIndex = page.getStartIndex();
		map.put("startIndex", startIndex);
		List<Role> role = roleMapper.queryPage(map);
		page.setDatas(role);
		Integer count = roleMapper.queryCount();
		page.setTotalSize(count);
		return page;
	}
	@Override
	public Integer deleteRole(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}
	@Override
	public Integer delBatch(Integer[] id) {
		return roleMapper.delBatch(id);
	}

}
