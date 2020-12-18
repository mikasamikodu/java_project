package com.atguigu.singleton.type6;

public class SingletonTest6 {
	public static void main(String[]args) {
		System.out.println("懒汉式（线程安全，双重检查）");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//懒汉式（线程安全，双重检查）,推荐使用
class Singleton{
//	1.构造器私有化，外部不能new
	private Singleton(){}
	
//	2.在类的内部创建对象的实例
	private static volatile Singleton SINGLETON;
//	volatile可认为是轻量级的synchronized,作用是防止指令重排。
//	一般创建对象会经过如下三个过程：1.分配空间；2.初始化对象；3.指向对象内存地址；
//	其中第二和第三步是有可能顺序颠倒的，加入volatile其中一个目的就是是过程强制按顺序执行，中间不会产生颠倒的事故
//	加入volatile的另一个目的是向主存同步SINGLETON的内存，通知其他线程SINGLETON的情况
//	volatile只可以用来修饰变量
	
//	3.拥有一个共有的静态方法，返回实例对象
	public static  Singleton getInstance() {
//		加入双重检查，解决线程安全的问题，同时解决懒加载的问题，提高了效率
		if(SINGLETON == null)	{
			synchronized (Singleton.class) {
				if(SINGLETON == null)	
					SINGLETON = new Singleton();
			}
		}
		return SINGLETON;
	}
}