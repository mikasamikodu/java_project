package com.atguigu.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    List<Integer> selectByPrimaryKey(Integer roleid);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);

	int deleteByRoleid(Integer roleid);

	int insertByid(@Param(value="roleid")Integer roleid, 
				   @Param(value="permissionid")Integer permissionid);
}