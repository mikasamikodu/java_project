package com.atguigu.factory.simplefactory.pizzastore.pizza;

public class GreekPizza extends Pizza {

	@Override
	public void prepare() {
		System.out.println("准备希腊披萨的原材料。。。");
	}
	
	public GreekPizza() {
		super.setName("希腊披萨");
	}
}
