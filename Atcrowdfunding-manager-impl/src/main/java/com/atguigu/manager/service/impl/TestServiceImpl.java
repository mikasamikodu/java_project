package com.atguigu.manager.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.manager.dao.TestDao;
import com.atguigu.manager.service.TestService;
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insert() {
		System.out.println("TestServiceImpl");
		Map map = new HashMap();
		map.put("name", "уехЩ");
		testDao.insert(map);
	}

}
