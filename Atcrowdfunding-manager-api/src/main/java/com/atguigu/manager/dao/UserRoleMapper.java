package com.atguigu.manager.dao;

import com.atguigu.bean.UserRole;
import com.atguigu.vo.Data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    void insert(@Param(value = "userid")Integer userid, 
    			@Param(value = "data")Data data);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);

	List<Integer> queryRoleByUserId(Integer id);

	void deleteByUR(@Param(value = "userid")Integer userid, 
					@Param(value = "data")Data data);
}