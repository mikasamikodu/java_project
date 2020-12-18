package com.atguigu.principle.segregation.improve;

public class Segregation2 {

	public static void main(String[] args) {
		A a = new A();
		a.opration1(new B());
		a.opration2(new B());
		a.opration3(new B());
		C c = new C();
		c.opration1(new D());
		c.opration4(new D());
		c.opration5(new D());
	}

}

interface interface1{
	void opration1();
}
interface interface2{
	void opration2();
	void opration3();
}
interface interface3{
	void opration4();
	void opration5();
}

class B implements interface1,interface2{
	public void opration1() {
		System.out.println("B操作了opration1");
	}
	public void opration2(){
		System.out.println("B操作了opration2");
	}
	public void opration3(){
		System.out.println("B操作了opration3");
	}
}
class D implements interface1,interface3{
	public void opration1() {
		System.out.println("D操作了opration1");
	}
	public void opration4(){
		System.out.println("D操作了opration4");
	}
	public void opration5(){
		System.out.println("D操作了opration5");
	}
}

class A{
	public void opration1(interface1 interface1){
		interface1.opration1();
	}
	public void opration2(interface2 interface2){
		interface2.opration2();
	}
	public void opration3(interface2 interface2){
		interface2.opration3();
	}
}
class C{
	public void opration1(interface1 interface1){
		interface1.opration1();
	}
	public void opration4(interface3 interface3){
		interface3.opration4();
	}
	public void opration5(interface3 interface3){
		interface3.opration5();
	}
}