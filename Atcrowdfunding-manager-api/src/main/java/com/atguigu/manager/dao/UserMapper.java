package com.atguigu.manager.dao;

import com.atguigu.bean.Member;
import com.atguigu.bean.User;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

	int doRegister(Member member);

	List<User> queryList(@Param(value="startIndex") Integer startIndex,
						 @Param(value="pageSize") Integer pageSize);

	Integer queryCount();
}