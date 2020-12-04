package com.design.pattern.factory;

public class FactoryMethod {
    public static void main(String[] args) {
//        Product product = SimpleFactory.createProduct("0");
//        product.method();
        AbstractProduct abstractProduct = new createProductA();
        Product product = abstractProduct.getProduct();
        product.method();

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
//提供一个工厂根据不同业务匹配具体实现业务
class SimpleFactory{
    public static Product createProduct(String type) {
        //预处理

        if ("0".equals(type)) {
            return new ProductA();
        }else{
            return null;
        }
    }
}
abstract class AbstractProduct {
    abstract ProductA createProduct();
    Product getProduct(){
        //预处理
        ProductA product = createProduct();
        //后处理
        return product;
    }

}

class createProductA extends AbstractProduct {

    @Override
    ProductA createProduct() {
        return new ProductA();
    }
}