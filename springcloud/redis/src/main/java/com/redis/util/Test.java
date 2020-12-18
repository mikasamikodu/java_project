package com.redis.util;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {

	private String url = "192.168.146.131";
	private int port = 6379;
	@org.junit.Test
	public void test1() {
		Jedis jedis = new Jedis(url, port);
		String auth = jedis.auth("root");
		System.out.println(auth);
		jedis.close();
	}
	
	@org.junit.Test
	public void test2() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(10);
		JedisPool pool = new JedisPool(config, url, port);
		Jedis jedis = pool.getResource();
		String auth = jedis.auth("root");
		System.out.println(auth);
		jedis.close();
	}
	
	@org.junit.Test
	public void test3() {//使用java操作redis的string
		Jedis jedis = JedisUtil.getJedis();
		Boolean flag = jedis.exists("runkey2");
		if(flag) {
			System.out.println("进入redis,得到"+jedis.get("runkey2"));
		}else {
			System.out.println("进入数据库"+jedis.set("runkey2", "root"));
		}
		jedis.close();
	}
	
	@org.junit.Test
	public void test4() {//使用java操作redis的hash
		Jedis jedis = JedisUtil.getJedis();
		if(jedis.exists("user")) {
			Map<String, String> map = jedis.hgetAll("user");
			System.out.println("进入redis,得到"+map);
		}else {
			jedis.hset("user", "name", "tom");
			jedis.hset("user", "age", "12");
			jedis.hset("user", "address", "北京");
		}
		jedis.close();
	}
}
