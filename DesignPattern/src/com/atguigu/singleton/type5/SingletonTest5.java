package com.atguigu.singleton.type5;

public class SingletonTest5 {
	public static void main(String[]args) {
		System.out.println("懒汉式（线程安全）");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//懒汉式（线程安全，同步代码块）
class Singleton{
//	1.构造器私有化，外部不能new
	private Singleton(){}
	
//	2.在类的内部创建对象的实例
	private static Singleton SINGLETON;
//	3.拥有一个共有的静态方法，返回实例对象(通过加入synchronized关键字，对这个方法加入同步锁机制)
//	虽然加入synchronized关键字，但是还是会产生SingletonTest3中的问题
	public static  Singleton getInstance() {
		if(SINGLETON == null) {
			synchronized(Singleton.class) {
				SINGLETON = new Singleton();
			}
		}
		return SINGLETON;
	}
}