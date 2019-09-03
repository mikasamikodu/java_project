package com.it.shop.util;

import java.util.UUID;
public class UUIDUtil {

	public static String getUUID() {       //得到一个不会重复的id,可以作为各表的主键id
		return UUID.randomUUID().toString();
	}
}
