package com.atguigu.principle.inversion;

public class DependencyInversion {

	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());

	}

}

class Email{
	public String getInfo() {
		return "hello world!";
	}
}
/**
 * 完成Person的接收消息的功能
 * 方案1分析
 * 1.简单，比较容易想到，
 * 2.如果我们获得的对象是新增的微信，短信，则新增类，同时Person类也要增加相应的接收方法
 * 3.解决思路：以入一个IReceiver类，表示接收者，这样Person类与IReceiver接口发生依赖，
 * 		因为email，WeiXin等都属于接收范围，他们各自实现IReceiver接口就可以了，这样我们就符合依赖倒转原则
 * @author miku
 *
 */
class Person{
	public void receive(Email email) {
		System.out.println(email.getInfo());
	}
}