package com.atguigu.potal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Member;
import com.atguigu.potal.dao.MemberMapper;
import com.atguigu.potal.service.MemberService;
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Member queryMemberLogin(Map<String, Object> map) {
		return memberMapper.queryMemberlogin(map);
	}

}
