package com.atguigu.singleton.type7;

public class SingletonTest7 {
	public static void main(String[]args) {
		System.out.println("懒汉式（线程安全，静态内部类）");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//懒汉式（线程安全，静态内部类）,推荐使用
class Singleton{
//	1.构造器私有化，外部不能new
	private Singleton(){}
	
//	2.构造静态内布类，在类的内部创建对象的实例（作为内部类保证了在Singleton类被加载时，自己不会被加载）
	private static  class SingletonInstance{
		private static final Singleton  SINGLETON = new Singleton();
	}
	
//	3.创建一个静态方法，返回SingletonInstance内部的静态实例对象
	public static  Singleton getInstance() {
		return SingletonInstance.SINGLETON;
		//（使用SingletonInstance内部的静态实例对象时，保证加载SingletonInstance类，由于是静态内部类所以只会加载一次，而且线程安全）
	}
}