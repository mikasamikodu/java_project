package com.atguigu.principle.segregation;

public class Segregation1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface interface1{
	void opration1();
	void opration2();
	void opration3();
	void opration4();
	void opration5();
}

class B implements interface1{
	public void opration1() {
		System.out.println("B操作了opration1");
	}
	public void opration2(){
		System.out.println("B操作了opration2");
	}
	public void opration3(){
		System.out.println("B操作了opration3");
	}
	public void opration4(){
		System.out.println("B操作了opration4");
	}
	public void opration5(){
		System.out.println("B操作了opration5");
	}
}
class D implements interface1{
	public void opration1() {
		System.out.println("D操作了opration1");
	}
	public void opration2(){
		System.out.println("D操作了opration2");
	}
	public void opration3(){
		System.out.println("D操作了opration3");
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
	public void opration2(interface1 interface1){
		interface1.opration2();
	}
	public void opration3(interface1 interface1){
		interface1.opration3();
	}
}
class C{
	public void opration4(interface1 interface1){
		interface1.opration4();
	}
	public void opration5(interface1 interface1){
		interface1.opration5();
	}
}