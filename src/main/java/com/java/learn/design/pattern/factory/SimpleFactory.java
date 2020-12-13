package com.java.learn.design.pattern.factory;

//提供一个工厂根据不同业务匹配具体实现业务  简单工厂模式
public class SimpleFactory {

    public static void main(String[] args) {
        Product product = createProduct("0");
        product.method();
    }
    public static Product createProduct(String type) {
        //预处理

        if ("0".equals(type)) {
            return new ProductA();
        }else{
            return null;
        }
    }
}
//一个接口  定义不变的方法
interface Product{
    void method();
}
//不同类型实现接口  体现了类的多态性
class ProductA implements Product{
    @Override
    public void method() {
        System.out.println("ProductA    method1");
    }
}
