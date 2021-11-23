package com.java.learn.interview;

public class StringBuilderRef {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("A");
        StringBuilder s2 = new StringBuilder("B");
        append(s1,s2);
        System.out.println(s1+","+s2);//AB,B
    }
    public static void append(StringBuilder x,StringBuilder y){
        x.append(y);
        y=x;
    }
}
