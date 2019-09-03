package com.itheima.proxy;

public class Producer implements IProducer {
    public void saleProduct(float money) {
        System.out.println("销售产品，得到钱："+money);
    }

    public void afterService(float money) {
        System.out.println("售后服务，得到钱："+money);

    }
}
