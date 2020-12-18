package com.atguigu.factory.simplefactory.pizzastore.pizza;

public abstract class Pizza {
	protected String name;//pizza名字

//	准备原材料，pizza类型不同，原材料不同
	public abstract void prepare();
	
	public void bake() {
		System.out.println(name + " baking...");
	}
	
	public void cut() {
		System.out.println(name + " cuting...");
	}
	
	public void box() {
		System.out.println(name + " boxing...");
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
