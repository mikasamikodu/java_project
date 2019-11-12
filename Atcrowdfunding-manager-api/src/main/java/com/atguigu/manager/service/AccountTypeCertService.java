package com.atguigu.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.AccountTypeCert;

public interface AccountTypeCertService {

	List<Map<String, Object>> findAll();

	int addACT(AccountTypeCert accountTypeCert);

	int deleteByACT(AccountTypeCert accountTypeCert);

	Map<String, Object> findByAcctType(String accttype);

}
