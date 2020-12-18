package com.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

	private static String url = "192.168.146.131";
	private static int port = 6379;
	private static JedisPool pool;
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(10);
		pool = new JedisPool(config, url, port);
	}
	
	public static Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.auth("root");
		return jedis;
	}
}
