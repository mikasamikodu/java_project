package com.atguigu.singleton.type2;

public class SingletonTest2 {
	public static void main(String[]args) {
		System.out.println("饿汉式（静态代码块）");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//饿汉式（静态代码块）
class Singleton{
//	1.构造器私有化，外部不能new
	private Singleton(){}
	
//	2.在类的内部创建对象的实例
	private static Singleton SINGLETON;
	
	static {//3.在静态代码块中初始化类的常量
		SINGLETON = new Singleton();
	}
//	4.拥有一个共有的静态方法，返回实例对象
	public static Singleton getInstance() {
		return SINGLETON;
	}
}