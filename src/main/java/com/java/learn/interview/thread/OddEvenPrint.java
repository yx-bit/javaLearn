package com.java.learn.interview.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印奇偶数
 */
public class OddEvenPrint  {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static Semaphore odd = new Semaphore(1);
    private static Semaphore even = new Semaphore(0);
    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    if (atomicInteger.get() >= 10) {
                        return;
                    }
                    odd.acquire();
                    System.out.println("thread1   进入" );
                    if (atomicInteger.get() % 2 != 0) {
                        System.out.println("thread1   " + atomicInteger.getAndAdd(1));
                    }
                    even.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    if (atomicInteger.get() > 10) {
                        return;
                    }
                    even.acquire();
                    System.out.println("thread2   进入" );
                    if (atomicInteger.get() % 2 == 0) {
                        System.out.println("thread2   " + atomicInteger.getAndAdd(1));
                    }
                    odd.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread2.start();

        thread1.start();
    }

}
