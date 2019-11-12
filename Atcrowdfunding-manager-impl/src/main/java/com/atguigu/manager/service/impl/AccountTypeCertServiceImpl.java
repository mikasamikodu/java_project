package com.atguigu.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.AccountTypeCert;
import com.atguigu.manager.dao.AccountTypeCertMapper;
import com.atguigu.manager.service.AccountTypeCertService;

@Service
public class AccountTypeCertServiceImpl implements AccountTypeCertService{

	@Autowired
	private AccountTypeCertMapper accountTypeCertMapper;
	
	@Override
	public List<Map<String, Object>> findAll() {
		return accountTypeCertMapper.selectAll();
	}

	@Override
	public int addACT(AccountTypeCert accountTypeCert) {
		return accountTypeCertMapper.insert(accountTypeCert);
	}

	@Override
	public int deleteByACT(AccountTypeCert accountTypeCert) {
		return accountTypeCertMapper.deleteByACT(accountTypeCert);
	}

	@Override
	public Map<String, Object> findByAcctType(String accttype) {
		return accountTypeCertMapper.findByAcctType(accttype);
	}

}
