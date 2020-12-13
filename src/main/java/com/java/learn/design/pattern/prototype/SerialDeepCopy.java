package com.java.learn.design.pattern.prototype;

import java.io.*;

public class SerialDeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        SerialBaseInfo baseInfo = new SerialBaseInfo();
        baseInfo.setCompanyName("xxx");
        SerialDeepProduct product = new SerialDeepProduct();
        product.setProductName("ccc");
        product.setBaseInfo(baseInfo);
        System.out.println("original:"+product);
        //序列化方式深拷贝  需要实现序列化 如例： baseInfo
        SerialDeepProduct clone = product.clone();
        clone.setProductName("ppp");
        clone.getBaseInfo().setCompanyName("yyy");
        System.out.println("copy:"+clone);
        //序列化方式对克隆对象的操作没有影响到原对象
        System.out.println("original:"+product);
    }
}

class SerialDeepProduct implements Cloneable,Serializable{
    private String productName;
    private SerialBaseInfo baseInfo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SerialBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(SerialBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    protected SerialDeepProduct clone() throws CloneNotSupportedException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        try(ObjectOutputStream oos = new ObjectOutputStream(bufferedOutputStream);) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SerialDeepProduct serialDeepProduct=null;
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BufferedInputStream bis = new BufferedInputStream(bais);
        try (ObjectInputStream ois = new ObjectInputStream(bis);){
            serialDeepProduct= (SerialDeepProduct)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serialDeepProduct;
    }

    @Override
    public String toString() {
        return "SerialDeepProduct{" +
                "productName='" + productName + '\'' +
                ", baseInfo=" + baseInfo +
                ", hashcode=" + this.hashCode() +
                '}';
    }
}


class SerialBaseInfo implements Cloneable,Serializable{
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    protected SerialBaseInfo clone() throws CloneNotSupportedException {
        return (SerialBaseInfo)super.clone();
    }

    @Override
    public String toString() {
        return "SerialBaseInfo{" +
                "companyName='" + companyName + '\'' +
                "，hashCode='" + this.hashCode() + '\'' +
                '}';
    }
}