package com.java.learn.design.pattern.singleton;

public class LazySingleton {
//    private volatile static LazySingleton instance;
    private  static LazySingleton instance;
    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
//            synchronized (LazySingleton.class) {
//                if (instance == null) {
//                    instance = new LazySingleton();
//                }
//            }
        }
        return instance;
    }
}
class LazySingletonTest{
    public static void main(String[] args) {
        //非内部类：无法new实例
//        LazySingleton lazySingleton = new LazySingleton();
        //单线程下 true
//        System.out.println(LazySingleton.getInstance() == LazySingleton.getInstance());
        //多线程下 概率返回不一致  导致线程安全问题
        //synchronizes加锁   需要双重check
        //volatile 防止指令重排序 编译器（jit） cpu 指令重排导致使用到尚未初始化的实例
        //1.内存分配
        //2.初始化
        //3.引用赋值
        for (int i = 0; i <2 ; i++) {
            new Thread(()->{
                LazySingleton instance = LazySingleton.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
