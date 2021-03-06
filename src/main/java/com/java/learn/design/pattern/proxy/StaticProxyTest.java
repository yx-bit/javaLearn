package com.java.learn.design.pattern.proxy;

public class StaticProxyTest {


    public static void main(String[] args) {
        ServiceProxy serviceProxy = new ServiceProxy(new ServiceImp());
        serviceProxy.test();
    }
    static class ServiceProxy implements ServiceInterface{
        ServiceInterface serviceInterface;
        public ServiceProxy(ServiceInterface serviceInterface) {
            this.serviceInterface = serviceInterface;
        }

        @Override
        public void test() {
            System.out.println("执行代理");
            serviceInterface.test();
        }
    }
    interface ServiceInterface{
         default void test(){
             System.out.println("执行接口方法");
         };
    }
    static class ServiceImp implements ServiceInterface{

    }
}



