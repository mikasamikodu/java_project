package com.itheima.test;

import org.junit.Test;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

public class Test2 {

	@Test
	public void test1() {
		AccountService user = new AccountServiceImpl();
		try {
			user.transfer("aaa", "bbb", 100);
			System.out.println("测试完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
