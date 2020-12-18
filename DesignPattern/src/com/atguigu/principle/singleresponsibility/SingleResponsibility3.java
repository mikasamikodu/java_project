package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility3 {

	public static void main(String[] args) {
		Vehicle2 vehicle = new Vehicle2();
		vehicle.run("摩托");
		vehicle.runWater("轮船");
		vehicle.runAir("飞机");
		/**
		 * 	摩托在公路上运行。。。
			轮船在大海上运行。。。
			飞机在天空中运行。。。

		 */
	}
}


/**
 * 方案32的分析
 * 1.这种修改方案没有对原来的类有大的改动，只是增加了方法
 * 2.这里虽然没有在类上遵循单一职责原则，但是在方法级别上，仍然遵循单一职责原则
 * 
 * @author miku
 *
 */
class Vehicle2{
	public void run(String vehicle) {
		System.out.println(vehicle + "在公路上运行。。。");
	}
	
	public void runAir(String vehicle) {
		System.out.println(vehicle + "在天空中运行。。。");
	}
	
	public void runWater(String vehicle) {
		System.out.println(vehicle + "在大海中运行。。。");
	}
}