package com.atguigu.springcloud.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atguigu.springcloud.dao.DeptDao;
import com.atguigu.springcloud.entities.Dept;

@SpringBootTest
public class Test01 {
	
	@Autowired
	DeptDao deptDao;
	
	@Test
	public void test02() {
		Dept dept = deptDao.findById(2L);
		System.out.println(dept);
	}

}
