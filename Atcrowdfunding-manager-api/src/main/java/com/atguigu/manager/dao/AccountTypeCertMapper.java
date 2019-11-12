package com.atguigu.manager.dao;

import com.atguigu.bean.AccountTypeCert;
import java.util.List;
import java.util.Map;

public interface AccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountTypeCert record);

    AccountTypeCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AccountTypeCert record);

	List<Map<String, Object>> selectAll();

	int deleteByACT(AccountTypeCert accountTypeCert);

	Map<String, Object> findByAcctType(String accttype);
}