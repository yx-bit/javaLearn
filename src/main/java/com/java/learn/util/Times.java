package com.java.learn.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Times {
    private static final SimpleDateFormat fmt=new SimpleDateFormat("HH:mm:ss.SSS");
    public interface Task{
        void execute(Integer[] source);
    }
    public static void test(String title,Task task,Integer[] source){
        if (task==null) return;
        title=(title==null)?"":("["+title+"]");
        System.out.println(title);
        Integer[] copy = Arrays.copyOf(source, source.length);
        Arrays.sort(copy);
        System.out.println("开始："+fmt.format(new Date()));
        long begin = System.currentTimeMillis();
        task.execute(source);
        long end = System.currentTimeMillis();
        System.out.println("结束："+fmt.format(new Date()));
        System.out.println("比较结果："+Arrays.equals(copy,source));
        double delta=(end-begin)/1000.0;
        System.out.println("耗时："+delta+"秒");
        System.out.println("--------------------------------------------");
    }
}
