package com.atguigu.potal.dao;

import java.util.List;

import com.atguigu.bean.MemberCert;

public interface MemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCert memberCert);

    MemberCert selectByPrimaryKey(Integer id);

	List<MemberCert> selectAll();
}