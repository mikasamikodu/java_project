package com.atguigu.potal.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.MemberCert;

public interface MemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCert memberCert);

    MemberCert selectByPrimaryKey(Integer id);

	List<MemberCert> selectAll();

	List<Map<String, Object>> getCertByMemberId(Integer memberid);
}