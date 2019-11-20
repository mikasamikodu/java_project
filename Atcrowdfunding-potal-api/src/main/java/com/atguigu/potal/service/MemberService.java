package com.atguigu.potal.service;

import java.util.Map;

import com.atguigu.bean.Member;

public interface MemberService {

	Member queryMemberLogin(Map<String, Object> map);

	int updateAccttype(Member member);

	int updateBaseinfo(Member loginMember);

	int doRegister(Member member);

	void updateEmail(String checkemail);

	void updateAuthStatus(Member loginMember);

	Member getMemberById(Integer memberid);

}
