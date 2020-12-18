package com.atguigu.factory.simplefactory.pizzastore.order;

import com.atguigu.factory.simplefactory.pizzastore.pizza.CheessPizza;
import com.atguigu.factory.simplefactory.pizzastore.pizza.GreekPizza;
import com.atguigu.factory.simplefactory.pizzastore.pizza.Pizza;

public class SimpleFactory {

	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if ("greek".equals(type)) {
			pizza = new GreekPizza();
		}else if ("cheess".equals(type)) {
			pizza = new CheessPizza();
		}
		return pizza;
	}
}
