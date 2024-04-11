package com.java.learn;

import org.springframework.stereotype.Component;

import java.util.concurrent.FutureTask;

@Component
public class Test1 implements Test {
    private String name="father";
    @Override
    public void execute() {
        System.out.println("Test1");
    }
    private static  int i=0;
    private static  Long l=2111L;
    private static  long ll=111312;
    private static  float ff=0.099f;
    private static  Float f=0.099f;
    private static  double dd=0.099;
    private static  Double d=0.099999;
    private static  short ss=9999;
    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j <300 ; j++) {
            new Thread(()->{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(i);
    }
}
