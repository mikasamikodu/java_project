package com.redis.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.bean.User;
import com.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/*
	 * 判断用户是否已被限制登陆
	 * */
	@Override
	public Map<String, Object> loginLock(String username) {
		String lockKey = User.getLoginLockKey(username);
		Map<String, Object> map = new HashMap<String, Object>();
		if(redisTemplate.hasKey(lockKey)) {
			map.put("flag", true);
			map.put("time", redisTemplate.getExpire(lockKey, TimeUnit.MINUTES));
		}else {
			map.put("flag", false);
		}
		return map;
	}

	/*
	 * 用户登录
	 * */
	@Override
	public boolean login(String username, String password) {
		return false;
	}

	/*
		3.登陆失败,进行次数的累计,同时进行登录失败的提示
		检查是否已有失败的key，没有则设置一个key，然后设置2分钟过期时间；
		如果有就检查key的值是否小于4.是的话就值加1，否的话就对登录做出限制，然后设置过期时间
	 * */
	@Override
	public String loginFail(String username) {
		String failKey = User.getLoginFailKey(username);
		String lockKey = User.getLoginLockKey(username);
		int expireTime = 2;
		int expireTime2 = 1;
		String result = "";
		int num = 4;
		if(redisTemplate.hasKey(failKey)) {//已有失败的key
			//如果有就检查key的值是否小于4.是的话就值加1，否的话就对登录做出限制，然后设置过期时间
			//检查key的值
			long count = Long.parseLong((String)redisTemplate.opsForValue().get(failKey));
			if(count<num) {//小于4.是的话就key值加1
				redisTemplate.opsForValue().increment(failKey, 1);
				num--;
				redisTemplate.getExpire(failKey, TimeUnit.SECONDS);
				result = "登陆失败！在"+expireTime+"秒内，最多再登录"+num+"次";
			}else {//否的话就对登录做出限制，然后设置过期时间
				redisTemplate.opsForValue().set(lockKey, "1");
				redisTemplate.expire(lockKey, expireTime2, TimeUnit.HOURS);
				result = "登录次数超过限制，"+username+"已被禁止登陆，禁止时间还有"+expireTime2+"小时";
			}
		}else {//设置一个key，然后设置过期时间；
			redisTemplate.opsForValue().set(failKey, "1");
			redisTemplate.expire(failKey, expireTime, TimeUnit.MINUTES);
			result = "登陆失败！在"+expireTime+"秒内，最多再登录"+num+"次";
		}
		return result;
	}
	
	/*
	 * 清空登陆失败的次数
	 * */
	@Override
	public void clearCount(String username) {
		String failKey = User.getLoginFailKey(username);
		if(redisTemplate.hasKey(failKey)) {
			redisTemplate.delete(failKey);
		}
	}

}
