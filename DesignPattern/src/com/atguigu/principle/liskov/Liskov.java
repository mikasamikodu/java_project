package com.atguigu.principle.liskov;

public class Liskov {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		System.out.println("4-1=" + a.func1(4, 1));
		System.out.println("4-1=" + b.func1(4, 1));
		System.out.println("4+1=" + b.func2(4, 1));
		
	}

}

class A{
	//返回两数的差
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

//b继承a，添加了两数之和的方法
class B extends A{
//	添加了两数之和的方法时，不小心重写了func1的方法
	public int func1(int num1, int num2) {
		return num1 + num2;
	}
	
	public int func2(int num1, int num2) {
		return func1(num1, num2) + 9;
	}
}