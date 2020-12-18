package com.atguigu.principle.liskov;

public class Liskov2 {

	public static void main(String[] args) {
		A1 a = new A1();
		B1 b = new B1();
		System.out.println("4-1=" + a.func1(4, 1));
		System.out.println("4-1=" + b.func1(4, 1));
		System.out.println("4+1=" + b.func2(4, 1));
		
	}

}

class Base{
	//返回两数的差
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}
class A1 extends Base{

}

//b继承a，添加了两数之和的方法
class B1 extends Base{
//	b在使用a的方法时可以使用组合的方式 
	private A1 a = new A1();
//	添加了两数之和的方法时，不小心重写了func1的方法
	public int func1(int num1, int num2) {
		return num1 + num2;
	}
	
	public int func2(int num1, int num2) {
		return func1(num1, num2) + 9;
	}
	
	public int func3(int num1, int num2) {
		return a.func1(num1, num2);
	}
}