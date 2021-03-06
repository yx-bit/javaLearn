package com.java.learn.design.pattern.proxy;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxyTest {

    public static void main(String[] args) {
        ServiceImp proxy = CglibDynamicProxy.getProxy(ServiceImp.class);
        System.out.println(proxy.getClass());
        proxy.test();
    }

    static class CglibDynamicProxy implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // 调用方法之前，我们可以添加自己的操作
            System.out.println("before method " + method.getName());
            // 通过反射调用委托类的方法
            Object object = methodProxy.invokeSuper(o, objects);
            // 调用方法之后，我们同样可以添加自己的操作
            System.out.println("after method " + method.getName());
            return object;
        }
        public static <T> T getProxy(Class<T> clazz) {
            // 创建动态代理增强类
            Enhancer enhancer = new Enhancer();
            // 设置类加载器
            enhancer.setClassLoader(clazz.getClassLoader());
            // 设置委托类（设置父类）
            enhancer.setSuperclass(clazz);
            // 设置方法拦截器
            enhancer.setCallback(new CglibDynamicProxy());
            // 创建代理类
            return (T) enhancer.create();
        }
    }
    interface ServiceInterface{
        default void test(){
            System.out.println("执行接口方法");
        };
    }
    static class ServiceImp implements ServiceInterface {

    }
}
