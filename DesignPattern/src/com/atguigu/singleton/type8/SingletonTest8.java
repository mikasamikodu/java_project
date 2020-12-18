package com.atguigu.singleton.type8;

public class SingletonTest8 {
	public static void main(String[]args) {
		System.out.println("枚举类");
		Singleton singleton = Singleton.SINGLETON;
		Singleton singleton2 = Singleton.SINGLETON;
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
		singleton.getInstance();
	}
}

//枚举类,推荐使用
enum Singleton{
	SINGLETON;

	public void getInstance() {
		System.out.println("ok~");
	}
}