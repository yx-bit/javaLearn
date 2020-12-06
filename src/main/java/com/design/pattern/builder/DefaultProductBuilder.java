package com.design.pattern.builder;


public class DefaultProductBuilder implements ProductBuilder{

    private  String productName;
    private  String productId;
    private int id;

    public DefaultProductBuilder() {
    }

    private DefaultProductBuilder(String productName, String productId, int id) {
        this.productName = productName;
        this.productId = productId;
        this.id = id;
    }

    @Override
    public ProductBuilder buildProductName(String productName) {
        this.productName=productName;
        return this;
    }

    @Override
    public ProductBuilder buildId(int id) {
        this.id=id;
        return this;
    }

    @Override
    public ProductBuilder buildProductId(String productId) {
        this.productId = productId;
        return this;
    }

    @Override
    public ProductBuilder build() {
        return new DefaultProductBuilder(this.productName,this.productId,this.id);
    }


    @Override
    public String toString() {
        return "DefaultProductBuilder{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", id=" + id +
                '}';
    }
}


interface ProductBuilder{
    ProductBuilder buildProductName(String productName);
    ProductBuilder buildId(int id);
    ProductBuilder buildProductId(String productId);
    ProductBuilder build();
}


class DefaultProductBuilderTest {
    public static void main(String[] args) {
//        DefaultProductBuilder builder = new DefaultProductBuilder();
//        ProductBuilder build = builder.buildProductId("0");
//        String s = build.toString();
//        System.out.println(s);
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{
                ProductBuilder builder = new DefaultProductBuilder();
                builder.buildProductId(finalI +"");
                builder.buildProductName("test"+finalI);
                builder.buildId(finalI);
                System.out.println(builder.toString());
            }).start();
        }
    }
}