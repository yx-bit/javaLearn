package com.design.pattern.prototype;

public class DeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setCompanyName("xxx");
        DeepProduct product = new DeepProduct();
        product.setProductName("ccc");
        product.setBaseInfo(baseInfo);
        System.out.println("original:"+product);
        //深拷贝  会对原浅拷贝的数据做特殊处理 如例： baseInfo
        DeepProduct clone = product.clone();
        clone.setProductName("ppp");
        clone.getBaseInfo().setCompanyName("yyy");
        System.out.println("copy:"+clone);
        //对克隆对象的操作没有影响到原对象
        System.out.println("original:"+product);
    }
}


class DeepProduct implements Cloneable{
    private String productName;
    private BaseInfo baseInfo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    protected DeepProduct clone() throws CloneNotSupportedException {
        DeepProduct product = (DeepProduct) super.clone();
        //手动实现非基本数据类型的克隆 则为深拷贝
        BaseInfo baseInfo = this.baseInfo.clone();
        product.setBaseInfo(baseInfo);
        return product;
    }

    @Override
    public String toString() {
        return "DeepProduct{" +
                "productName='" + productName + '\'' +
                ", baseInfo=" + baseInfo +
                ", hashcode=" + this.hashCode() +
                '}';
    }
}
