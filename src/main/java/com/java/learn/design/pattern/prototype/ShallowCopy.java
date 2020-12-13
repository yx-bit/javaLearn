package com.java.learn.design.pattern.prototype;

public class ShallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setCompanyName("xxx");
        Product product = new Product();
        product.setProductName("ccc");
        product.setBaseInfo(baseInfo);
        System.out.println("original:"+product);
        //浅拷贝 如果全是基本数据类型  则为深拷贝 否则则为浅拷贝  如例： baseInfo
        Product clone = product.clone();
        clone.setProductName("ppp");
        clone.getBaseInfo().setCompanyName("yyy");
        System.out.println("copy:"+clone);
        //对克隆对象的操作影响到了原对象
        System.out.println("original:"+product);
    }
}

class Product implements Cloneable{
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
    protected Product clone() throws CloneNotSupportedException {
        return (Product)super.clone();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", baseInfo=" + baseInfo +
                ", hashcode=" + this.hashCode() +
                '}';
    }
}
class BaseInfo implements Cloneable{
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return (BaseInfo)super.clone();
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "companyName='" + companyName + '\'' +
                "，hashCode='" + this.hashCode() + '\'' +
                '}';
    }
}