package com.atguigu.potal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MemberCert;
import com.atguigu.potal.dao.MemberCertMapper;
import com.atguigu.potal.service.MemberCertService;
@Service
public class MemberCertServiceImpl implements MemberCertService {

	@Autowired
	private MemberCertMapper memberCertMapper;
	
	@Override
	public void saveMC(MemberCert cert) {
		memberCertMapper.insert(cert);
	}

	@Override
	public List<Map<String, Object>> getCertByMemberId(Integer memberid) {
		return memberCertMapper.getCertByMemberId(memberid);
	}

}
