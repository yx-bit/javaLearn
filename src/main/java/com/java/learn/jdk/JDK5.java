package com.java.learn.jdk;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.*;
import static java.lang.Math.*;

/**
 * Java SE 5.0 (1.5.0)	Tiger（老虎） 2004-09-30
 */
public class JDK5 {


    public static void main(String[] args) throws Exception {
        introspector();
        Thread thread = new Thread();
    }

    /**
     * 自动装箱拆箱
     */
    public static void autoBoxUnBox(){
        //jdk 1.5 以前
        //装箱
        int a=1;
        Integer a1 = new Integer(1);
        //拆箱
        Integer b = new Integer(1);
        int b1 = b.intValue();
        //jdk 1.5 
        //装箱
        int c=1;
        Integer c1 = c;
        //拆箱
        Integer d = new Integer(1);
        int d1 = d;
    }

    /**
     * 枚举
     */
    public static void enums(){
        //jdk5 以前使用常量
        System.out.println(Const.RABBIT);
        //jdk5 使用常量
        System.out.println(Animal.RABBIT);
    }
    class Const {
        public static final String RABBIT = "RABBIT";
        public static final String CAT = "CAT";
    }
    enum Animal {
        RABBIT,CAT;
    }

    /**
     * foreach
     */
    public static void foreach(){
        String[] strings={"1","2","3"};
        //jdk5 以前遍历
        for (int i = 0; i < strings.length; i++) {
            
        }
        //jdk5 新增foreach遍历
        for (String string : strings) {
            
        }
    }

    /**
     * 可变参数
     */
    public static void testVarargs(){
        //jdk 1.5 以前
        addArr(new String[]{"1","2","3"});
        //jdk 1.5
        testVarargs("10","20","30");
    }
    public static void addArr(String[] args){
        for (String arg : args) {
            System.out.println(arg);
        }
    }
    public static void testVarargs(String ...args){
        for (String arg : args) {
            System.out.println(arg);
        }
    }
    /**
     * 静态导入
     */
    public static void staticImport(){
        //import static java.lang.System.*;
        //import static java.lang.Math.*;
        out.println(max(0,1));
    }
    /**
     * 泛型
     */
    public static void generics(){
        //jdk 1.5 以前
        List list=new ArrayList();
        //jdk 1.5
        List<String> listGenerics=new ArrayList<String>();
    }

    static class User {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * 内省（Introspector） 是Java语言对JavaBean类属性、事件的处理方法封装的操作api
     * 本质上是利用java的反射技术
     * @throws Exception
     */
    public static void introspector() throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("name", "王小二");
        User user = new User();
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method method = propertyDescriptor.getWriteMethod();
            if (method ==null) {
                continue;
            }
            method.invoke(user, map.get(propertyDescriptor.getName()));
        }
        out.println(user);
    }
}
