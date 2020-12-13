package com.java.learn.design.pattern.singleton;

public class HungurySingleton {
    private static HungurySingleton instance = new HungurySingleton();

    private HungurySingleton() {
    }

    public static HungurySingleton getInstance() {
        return instance;
    }
}
 class HungurySingletonTest{
     public static void main(String[] args) {
         //true  类加载初始化赋值 借助jvm类加载机制，保证实例唯一性
         //类加载过程
         //1. 加载二进制数据到内存中，生成对应的Class数据结构
         //2.链接 a 验证 b 准备（给类的静态成员变量赋默认值） c 解析
         //3. 初始化 给类的静态变量赋初始值
//         System.out.println(HungurySingleton.getInstance()==HungurySingleton.getInstance());
         for (int i = 0; i <2 ; i++) {
             new Thread(()->{
                 HungurySingleton instance = HungurySingleton.getInstance();
                 System.out.println(instance);
             }).start();
         }
     }

}
