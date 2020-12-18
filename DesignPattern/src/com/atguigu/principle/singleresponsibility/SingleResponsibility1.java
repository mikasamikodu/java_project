package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility1 {

	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.run("摩托");
		vehicle.run("轮船");
		vehicle.run("飞机");
		/**
		 * 	摩托在公路上运行。。。
			轮船在公路上运行。。。
			飞机在公路上运行。。。
		 */
	}
}


/**
 * 交通工具类
 * 方式1
 * 1.在方式1中方法中，违反了单一职责的原则
 * 2.解决方案也非常简单，根据交通工具运行方法的不同分解成不同的类即可
 * @author miku
 *
 */
class Vehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "在公路上运行。。。");
	}
}