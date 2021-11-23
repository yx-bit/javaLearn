package com.java.learn.interview.thread;

import com.java.learn.interview.thread.dynamic.ResizeableLinkedBlockingQueue;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadTest {

    public static void main(String[] args) {
        //MultiValueMap<Integer, Integer> multiValueMap = new LinkedMultiValueMap();
        //for (int i = 1000; i < 2000; i++) {
        //    int x = i % 15;
        //    multiValueMap.add(x, i);
        //}
        //for (Map.Entry<Integer, List<Integer>> map : multiValueMap.entrySet()) {
        //    System.out.println(map.getKey()+":"+map.getValue().size());
        //}
        ResizeableLinkedBlockingQueue<Integer> queue = new ResizeableLinkedBlockingQueue<>(10000);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //for (int i = 1; i <10000 ; i++) {
        //    int finalI = i;
        //    new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            try {
        //                countDownLatch.await();
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            queue.add(finalI);
        //        }
        //    }).start();
        //}
        //for (int i = 1; i <10000 ; i++) {
        //    new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            try {
        //                countDownLatch.await();
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            queue.poll();
        //        }
        //    }).start();
        //}
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            countDownLatch.await();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        for (int i = 1; i <10000 ; i++) {
        //            queue.setCapacity(i *2);
        //        }
        //    }
        //}).start();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        for (;;) {
        //            try {
        //                Thread.sleep(1);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            System.out.println(queue.getCapacity()+":"+queue.remainingCapacity()+":"+queue.size());
        //        }
        //    }
        //}).start();
        //countDownLatch.countDown();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    queue.setCapacity(new Random().nextInt(1000));
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println(queue.getCapacity() + ":" + queue.remainingCapacity() + ":" + queue.size());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    queue.add(new Random().nextInt(1000));
                }
            }
        }).start();
    }
}
