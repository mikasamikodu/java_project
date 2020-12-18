package com.atguigu.factory.simplefactory.pizzastore.pizza;

public class CheessPizza extends Pizza {

	@Override
	public void prepare() {
		System.out.println("准备奶酪披萨的原材料。。。");
	}
	
	public CheessPizza() {
		super.setName("奶酪披萨");
	}
}
