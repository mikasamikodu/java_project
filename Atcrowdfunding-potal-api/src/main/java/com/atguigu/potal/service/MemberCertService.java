package com.atguigu.potal.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.MemberCert;

public interface MemberCertService {

	void saveMC(MemberCert cert);

	List<Map<String, Object>> getCertByMemberId(Integer memberid);

}
