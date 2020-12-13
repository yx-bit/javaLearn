package com.java.learn.design.pattern.singleton;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

//本质上市利用类的加载机制来保证线程安全
//实际使用  才触发类的初始化   也是懒加载的一种形式
public class InnerClassSingleton implements Serializable {
    //序列化版本号配置
    private static final long serialVersionUID = -6547156540469463812L;

    private static class InnerClassHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
        //防止反射攻击单例
        if (InnerClassHolder.instance != null) {
            throw new RuntimeException("单例不允许多实例");
        }
    }

    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.instance;
    }
    //对象流序列化自定义实现返回实例
    Object readResolve() throws ObjectStreamException {
        return InnerClassHolder.instance;
    }

    private String name;
}

class InnerClassSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        /*单线程*/
//        System.out.println(InnerClassSingleton.getInstance()==InnerClassSingleton.getInstance());
        /*多线程*/
//        for (int i = 0; i <2 ; i++) {
//            new Thread(()->{
//                InnerClassSingleton instance = InnerClassSingleton.getInstance();
//                System.out.println(instance);
//            }).start();
//        }
        /*反射攻击实例*/
//        Constructor<InnerClassSingleton> declaredConstructor = InnerClassSingleton.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);//获取访问权限
//        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();
//        InnerClassSingleton instance = InnerClassSingleton.getInstance();
//        System.out.println(instance==innerClassSingleton);
        //测试序列化
        InnerClassSingleton instance = InnerClassSingleton.getInstance();
        //1.输出文件
        ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(InnerClassSingleton.class.getSimpleName()));
        oss.writeObject(instance);
        oss.flush();
        oss.close();
        //2.输入文件
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(InnerClassSingleton.class.getSimpleName()));
        InnerClassSingleton innerClassSingleton = (InnerClassSingleton)ois.readObject();
        ois.close();
        System.out.println(instance==innerClassSingleton);
    }

}