package com.java.learn.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CountDownLatchTest {
    @Data
    static
    class DataTab{
        private String code;
        private String value;
    }
    public static void main(String[] args) throws InterruptedException {
        List<DataTab> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataTab ab = new DataTab();
            ab.setCode(i+"");
            list.add(ab);
        }
        int requestLimit = (list.size()/3) +(list.size()%3 == 0 ? 0:1);
        CountDownLatch countDownLatch = new CountDownLatch(requestLimit);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= requestLimit; i++) {
            executorService.execute(new FormateDataRunable(list.stream().skip(3 * (i - 1)).limit(3).collect(Collectors.toList()),countDownLatch));
        }
        countDownLatch.await();
        System.out.println(list);
        executorService.shutdown();
    }
    @AllArgsConstructor
    @Data
    static
    class FormateDataRunable implements Runnable {
        private List<DataTab> list;
        private CountDownLatch countDownLatch;
        @Override
        public void run() {
            try {
                list.forEach(item -> {
                    String code = item.getCode();
                    if (code.equals("9")) {
                        throw new RuntimeException("");
                    }
                    item.setValue(code +1);
                });
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
