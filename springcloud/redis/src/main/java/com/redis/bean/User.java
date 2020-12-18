package com.redis.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;
//@Table
//@Entity
@Component
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//private int id;
	public static String getLoginLockKey(String username) {
		return "user:login:lock:time:"+username;
	}
	
	public static String getLoginFailKey(String username) {
		return "user:login:fail:count:"+username;
	}
}
