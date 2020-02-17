package com.atguigu.manager.service;

import java.util.List;

import com.atguigu.bean.Cert;

public interface CertService {

	List<Cert> findAll();

	List<Cert> findByAccttype(String accttype);

	Cert getCertById(Integer certid);

}
