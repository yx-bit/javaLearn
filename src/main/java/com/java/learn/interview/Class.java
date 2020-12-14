package com.java.learn.interview;

public class Class {
    public static void main(String[] args) {
        String[] strings = new String[1];
        int length = strings.length;
        System.out.println(length);
    }
}
//如果一个类被final修饰，内部字段可以修改  类不能被继承
final class FinalClass {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}