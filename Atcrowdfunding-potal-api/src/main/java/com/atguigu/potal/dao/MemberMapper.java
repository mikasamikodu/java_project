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

	int updateAccttype(Member member);

	int updateBaseinfo(Member loginMember);

	void updateEmail(String checkemail);

	void updateAuthStatus(String string);
}