package com.design.pattern.factory;

//工厂方法模式
public class FactoryMethod {
    public static void main(String[] args) {
        AbstractProduct abstractProduct = new CreateProductA();
        Product product = abstractProduct.getProduct();
        product.method();

    }

}
//定义一个抽象的模式
abstract class AbstractProduct {
    abstract Product createProduct();
    Product getProduct(){
        //预处理
        Product product = createProduct();
        //后处理
        return product;
    }

}
//不同类型继承抽象类  体现多态性
class CreateProductA extends AbstractProduct {

    @Override
    Product createProduct() {
        return new ProductA();
    }
}