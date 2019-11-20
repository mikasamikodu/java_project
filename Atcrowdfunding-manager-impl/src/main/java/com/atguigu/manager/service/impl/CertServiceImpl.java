package com.atguigu.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Cert;
import com.atguigu.manager.dao.CertMapper;
import com.atguigu.manager.service.CertService;
@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private CertMapper certMapper;
	@Override
	public List<Cert> findAll() {
		return certMapper.selectAll();
	}
	@Override
	public List<Cert> findByAccttype(String accttype) {
		return certMapper.findByAccttype(accttype);
	}
	@Override
	public Cert getCertById(Integer certid) {
		return certMapper.selectByPrimaryKey(certid);
	}

}
