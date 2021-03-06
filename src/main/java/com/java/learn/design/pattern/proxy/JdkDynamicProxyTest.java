package com.java.learn.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ServiceInterface proxy = JdkInvocationHandler.getProxy(ServiceImp.class);
        System.out.println(proxy.getClass());
        proxy.test();

    }

    static class JdkInvocationHandler<T> implements InvocationHandler{

        // 将委托类注入处理类（这里我们用 Object 代替，方便扩展）
        private T target;

        public JdkInvocationHandler(T target) {
            this.target = target;
        }

        // 重写 invoke 方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            //调用方法之前，我们可以添加自己的操作
            System.out.println("before method " + method.getName());
            Object result = method.invoke(target, args);
            //调用方法之后，我们同样可以添加自己的操作
            System.out.println("after method " + method.getName());
            return result;
        }

        public static <T> T getProxy(Class<T> object) throws IllegalAccessException, InstantiationException {
            return (T)Proxy.newProxyInstance(
                    object.getClassLoader(),
                    object.getInterfaces(),
                    new JdkInvocationHandler<>(object.newInstance())
            );
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
