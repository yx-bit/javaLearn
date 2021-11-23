package com.java.learn.interview;

import java.util.HashMap;
import java.util.Map;

public class StringAndMap {
    public static void main(String[] args) {
        String s1 = new String("123");
        String s2 = new String("123");
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put(s1,"abc");
        System.out.println(s1==s2);//false  new String 内存地址不一致
        System.out.println(stringMap.get(s2));//get 比较的是equals 和hashcode

    }
}
