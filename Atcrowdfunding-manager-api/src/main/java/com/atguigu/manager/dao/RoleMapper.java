package com.atguigu.manager.dao;

import com.atguigu.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	List<Role> queryPage(Map<String, Object> map);

	Integer queryCount(Map<String, Object> map);

	Integer delBatch(Integer[] id);
}