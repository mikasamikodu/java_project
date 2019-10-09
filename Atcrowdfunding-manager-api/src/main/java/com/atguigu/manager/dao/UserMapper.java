package com.atguigu.manager.dao;

import com.atguigu.bean.Member;
import com.atguigu.bean.Permission;
import com.atguigu.bean.User;
import com.atguigu.vo.Data;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

	int doRegister(Member member);

	List<User> queryList(Map<String,Object> map);

	Integer queryCount(Map<String, Object> paramMap);

	int deleteBatchUser(Data data);

	List<Permission> queryPermissionsByUserId(Integer id);
}