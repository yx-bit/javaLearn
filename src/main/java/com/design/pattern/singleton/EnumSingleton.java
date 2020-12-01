package com.design.pattern.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingleton {
    INSTANCE,

}

class EnumSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        //单线程
//        System.out.println(EnumSingleton.INSTANCE==EnumSingleton.INSTANCE);
        //反射攻击实例
//        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
//        declaredConstructor.setAccessible(true);
//        EnumSingleton enumSingleton = declaredConstructor.newInstance("INSTANCE",0);
//        System.out.println(enumSingleton==EnumSingleton.INSTANCE);
        //测试序列化
        EnumSingleton instance = EnumSingleton.INSTANCE;
        //1.输出文件
        ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(EnumSingleton.class.getSimpleName()));
        oss.writeObject(instance);
        oss.flush();
        oss.close();
        //2.输入文件
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(EnumSingleton.class.getSimpleName()));
        EnumSingleton enumSingleton = (EnumSingleton)ois.readObject();
        ois.close();
        System.out.println(instance==enumSingleton);
    }
}