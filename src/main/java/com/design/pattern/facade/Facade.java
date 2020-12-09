package com.design.pattern.facade;

public class Facade {
    Server1 server1=new Server1();
    Server2 server2=new Server2();
    public static void main(String[] args) {
        Client.callServer();
    }
    //提供通用调用的模式
    public void doSomething() {
        server1.method();
        server2.method();
    }
}
class Client{
    //门面模式
    static Facade facade=new Facade();

    public static void callServer() {
        facade.doSomething();
    }
}

class Server1{
    public void method(){
        System.out.println("server1   method ");
    }
}
class Server2{
    public void method(){
        System.out.println("server2   method");
    }
}