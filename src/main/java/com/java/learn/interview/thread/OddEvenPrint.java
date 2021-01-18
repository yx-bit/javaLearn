package com.java.learn.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印奇偶数
 */
public class OddEvenPrint  {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            while (true) {
                if (atomicInteger.get() > 100) {
                    return;
                }
                if (atomicInteger.get() % 2 != 0) {
                    System.out.println("thread1   " + atomicInteger.getAndAdd(1));
                }
                LockSupport.park();
            }

        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                if (atomicInteger.get() > 100) {
                    return;
                }
                if (atomicInteger.get() % 2 == 0) {
                    System.out.println("thread2   " + atomicInteger.getAndAdd(1));
                    LockSupport.unpark(thread1);
                }
            }

        });
        thread1.start();
        thread2.start();
    }

}
