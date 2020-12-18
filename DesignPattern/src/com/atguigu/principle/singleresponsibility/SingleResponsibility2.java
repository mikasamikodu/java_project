package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility2 {

	public static void main(String[] args) {
		RoadVehicle vehicle = new RoadVehicle();
		vehicle.run("摩托");
		WaterVehicle vehicle2 = new WaterVehicle();
		vehicle2.run("轮船");
		AirVehicle vehicle3 = new AirVehicle();
		vehicle3.run("飞机");
		/**
		 * 	摩托在公路上运行。。。
			轮船在大海上运行。。。
			飞机在天空中运行。。。

		 */
	}
}


/**
 * 方案2的分析
 * 1.遵守了单一职责的原则
 * 2.但是这样的改动很大，既要修改类，又要修改客户端
 * 3.改进：修改Vehicle类，改动的代码比较少
 * @author miku
 *
 */
class RoadVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "在公路上运行。。。");
	}
}
class WaterVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "在大海上运行。。。");
	}
}
class AirVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "在天空中运行。。。");
	}
}