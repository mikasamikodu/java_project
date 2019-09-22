package com.atguigu.manager.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.manager.dao.TestDao;
import com.atguigu.manager.service.TestService;

public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	@Override
	public void insert() {
		Map<String, Object> map = new HashMap<String,  Object>();
		map.put("name", "张三");
		testDao.insert(map);
	}

}
