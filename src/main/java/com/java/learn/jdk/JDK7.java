package com.java.learn.jdk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class JDK7 {
    public static void main(String[] args) throws Exception {
        forkJoinFrame();
    }

    /**
     * switch支持String字符串类型
     * @param str
     */
    public static void switchString(String str){
        switch (str) {
            case "1":break;
            case "2":break;
            default:break;
        }
    }

    /**
     * try-with-resources 资源自动关闭
     * @return
     * @throws IOException
     */
    public static String tryWithResources() throws IOException {
        int i=0;
        //前
        BufferedReader br = new BufferedReader(new FileReader("d://1.txt"));
        try {
            if (i == 2) {
                return br.readLine();
            }
        }finally {
            br.close();
        }
        //后
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("d://1.txt"))) {
            return bufferedReader.readLine();
        }
    }

    /**
     * 整数类型如（byte，short，int，long）能够用二进制来表示
     */
    public static void intToByteDisplay(){
        int a=0b010;
        int b=0B010;
    }

    /**
     * 数字常量支持下划线
     */
    public static void intCan_(){
       int a=111_111;
    }

    /**
     *  泛型实例化类型自动推断,即”<>”
     */
    public static void genericAutoDeduce(){
        //前
        List<String> list = new ArrayList<String>();
        //后
        List<String> listAfter = new ArrayList<>();
    }

    /**
     * 一个catch中捕获多个异常类型，用（|）分隔开
     */
    public static void multiCatch(){
        //前
        try {

        } catch (NullPointerException e) {

        } catch (ArrayIndexOutOfBoundsException e) {

        }
        //后
        try {
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {

        }
    }

    /**
     * Java7 提供了全新的NIO2.0 API，方便文件管理的编码。
     * 如，可以在java.nio.file包下使用Path、Paths、Files、WatchService等常用类型。
     */
    public static void FilesApi() throws IOException {
        Path path = Paths.get("C:\\jay\\七里香.txt"); //创建Path对象
        byte[] bytes= Files.readAllBytes(path);  //读取文件
        System.out.println(path.getFileName()); //获取当前文件名称
        System.out.println(path.toAbsolutePath()); // 获取文件绝对路径
        System.out.println(new String(bytes, "utf-8"));
    }

    /**
     * Java7提供的一个用于并行执行任务的框架，是一个把大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架。
     */
    public static void forkJoinFrame() throws ExecutionException, InterruptedException {
        // Fork/Join框架的线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<?> submit = pool.submit(JDK7::run);

        Object o = submit.get();//无 返回null
        System.out.println(o);
    }

    private static String run() {
        System.out.println(1);
        int i=1;
        if (i == 1) {
            throw new RuntimeException("test");
        }
        return "2";
    }
}
