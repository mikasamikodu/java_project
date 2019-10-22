package com.atguigu.potal.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Member record);

    Member queryMemberlogin(Map<String, Object> paramMap);

	int doRegister(Member member);
	
	List<Member> selectAll();
}