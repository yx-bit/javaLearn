package com.java.learn.interview.lambda;

import java.lang.reflect.Method;

public class TestReflect {
    public static void main(String[] args) throws Exception {
//        Class<?> aClass = Class.forName("com.java.learn.interview.lambda.StreamTest");
//        Object o = aClass.newInstance();
        StreamTest streamTest = new StreamTest();
        Method[] methods = streamTest.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(method.invoke(null));
        }
    }
}
