package com.atguigu.potal.service;

import java.util.Map;

import com.atguigu.bean.Member;

public interface MemberService {

	Member queryMemberLogin(Map<String, Object> map);

}
