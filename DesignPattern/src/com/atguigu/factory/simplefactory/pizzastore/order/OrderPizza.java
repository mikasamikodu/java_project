package com.atguigu.factory.simplefactory.pizzastore.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.atguigu.factory.simplefactory.pizzastore.pizza.CheessPizza;
import com.atguigu.factory.simplefactory.pizzastore.pizza.GreekPizza;
import com.atguigu.factory.simplefactory.pizzastore.pizza.Pizza;

public class OrderPizza {
	
//	public OrderPizza() {
//		Pizza pizza = null;
//		String type = null;//披萨类型
//		do {
//			type = getType();
//			if ("greek".equals(type)) {
//				pizza = new GreekPizza();
//			}else if ("cheess".equals(type)) {
//				pizza = new CheessPizza();
//			}else {
//				break;
//			}
////			订购披萨后，开始制作披萨
//			pizza.prepare();
//			pizza.bake();
//			pizza.cut();
//			pizza.box();
//		} while (true);
//	}
	private SimpleFactory simpleFactory;
	private Pizza pizza =null;
	
	public OrderPizza(SimpleFactory simpleFactory) {
		setSimpleFactory(simpleFactory);
	}
	
	public void setSimpleFactory(SimpleFactory simpleFactory) {
		this.simpleFactory = simpleFactory;
		String type = null;
		
		do {
			type = getType();
			pizza = this.simpleFactory.createPizza(type);
			if (pizza != null) {
				pizza.prepare();
				pizza.bake();
				pizza.cut();
				pizza.box();
			}else {
				break;
			}
		} while (true);
	}
//	从控制台获取披萨种类
	private String getType() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("请输入披萨种类：");
			String type = reader.readLine();
			return type;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
