package com.atguigu.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Type;
import com.atguigu.manager.dao.TypeMapper;
import com.atguigu.manager.service.TypeService;
@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public List<Type> findAll() {
		return typeMapper.selectAll();
	}

}
