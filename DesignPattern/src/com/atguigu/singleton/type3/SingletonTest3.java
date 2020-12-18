package com.atguigu.singleton.type3;

public class SingletonTest3 {
	public static void main(String[]args) {
		System.out.println("懒汉式（线程不安全）");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//懒汉式（线程不安全）
class Singleton{
//	1.构造器私有化，外部不能new
	private Singleton(){}
	
//	2.在类的内部创建对象的实例
	private static Singleton SINGLETON;
//	3.拥有一个共有的静态方法，返回实例对象
//	假如一个线程进入if判断开始创建实例，但同时另一个线程也进入if判断开始创建实例，
//	这样就会创建多次实例，达不到只创建一次实例的目的，因此是线程不安全的
	public static Singleton getInstance() {
		if(SINGLETON == null) {
			SINGLETON = new Singleton();
		}
		return SINGLETON;
	}
}