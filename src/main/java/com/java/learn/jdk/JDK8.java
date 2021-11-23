package com.java.learn.jdk;

import com.google.common.base.Function;
import io.lettuce.core.output.KeyStreamingChannel;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JDK8 {

    public static void main(String[] args) {
        //functionInterface();
        //methodReference();
        //streamApi();
        //optional();
        //timeApi();
        //repeatableAnnotation();
        base64();
    }

    /**
     * lanbda表达式
     */
    public static void lambda(){
        Arrays.asList(1,2,3).forEach(System.out::println);
    }

    @FunctionalInterface
    public interface Runnable {
        void run();
    }

    static class RunnableImpl  {
        public RunnableImpl() {
        }
        public RunnableImpl(Runnable runnable) {
            runnable.run();
        }
    }

    /**
     * 函数式接口
     */
    public static void functionInterface(){
        new RunnableImpl(() -> {
            System.out.println(111);
        });
        new Thread(new FutureTask<String>(()->{
            System.out.println(11);
            return "";
        }));
    }
    public  void instanceMethod(String s){
        new Thread(new FutureTask<String>(()->{
            System.out.println(s);
            return s;
        })).start();
    }
    /**
     * 方法引用
     */
    public static void methodReference(){
        //静态方法引用
        Consumer<String> consumer=System.out::println;
        consumer.accept("test");
        //构造器引用
        Supplier<List<String>> supplier=ArrayList::new;
        System.out.println(supplier.get().hashCode());
        //特定对象的实例方法引用
        JDK8 jdk8 = new JDK8();
        KeyStreamingChannel<String> instanceMethod = jdk8::instanceMethod;
        instanceMethod.onKey("你牛逼了");
        //实例方法引用
        Function<String, Integer> stringIntegerFunction = String::length;
        System.out.println(stringIntegerFunction.apply("11"));
    }

    /**
     * 默认方法
     */
    interface defaultMethod {
        default boolean support() {
            return true;
        }
    }

    /**
     * Stream API，支持对元素流进行函数式操作，它集成在Collections API 中，可以对集合进行批量操作。常用API：
     *
     * filter 筛选
     * map流映射
     * reduce 将流中的元素组合起来
     * collect 返回集合
     * sorted 排序
     * flatMap 流转换
     * limit返回指定流个数
     * distinct去除重复元素
     */
    public static void streamApi(){

        List<Singer> singerList = new ArrayList<Singer>();
        singerList.add(new Singer("jay", 11, 36));
        singerList.add(new Singer("eason", 8, 31));
        singerList.add(new Singer("JJ", 6, 29));

        List<String> singerNameList = singerList.stream()
                .filter(singer -> singer.getAge() > 30)  //筛选年龄大于30
                .sorted(Comparator.comparing(Singer::getSongNum))  //根据歌曲数量排序
                .map(Singer::getName)  //提取歌手名字
                .collect(Collectors.toList()); //转换为List
        Map<String, Singer> collect = singerList.stream()
                .flatMap(x -> Stream.of(new Singer[]{x})).collect(Collectors.toMap(Singer::getName, singer -> singer));
        Stream.of(new String[] {"Hello", "World"}).flatMap(str -> Arrays.stream(str.split(""))).distinct()
                .collect(Collectors.toList()).forEach(System.out::println);
    }
    public static class Singer {

        private String name;
        private Integer songNum;
        private Integer age;

        public Singer(String name, Integer songNum, Integer age) {
            this.name = name;
            this.songNum = songNum;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSongNum() {
            return songNum;
        }

        public void setSongNum(Integer songNum) {
            this.songNum = songNum;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    /**
     * 解决NPE问题 代替if else 的Optional类
     */
    public static void optional(){
        Optional.ofNullable("ss").ifPresent(System.out::println);
    }

    /**
     * 新的日期api
     */
    @Test(type = "Date Time")
    @Test(type="java 8")
    public static void timeApi() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        System.out.println("今年是" + year);
        //是否闰年
        System.out.println("今年是不是闰年:" + today.isLeapYear());

        LocalDateTime todayTime = LocalDateTime.now();
        System.out.println("当前时间" + todayTime);
        //时区指定
        System.out.println("美国时间:" + ZonedDateTime.of(todayTime, ZoneId.of("America/Los_Angeles")));
        //时间计算
        LocalDate specailDate = LocalDate.of(2020, 6, 20);
        LocalDate expectDate = specailDate.plus(100, ChronoUnit.DAYS);
        System.out.println("比较特别的一天" + specailDate);
        System.out.println("特殊日期的100天" + expectDate);
    }

    /**
     * 重复注解，即一个注解可以在一个类、属性或者方法上同时使用多次；用@Repeatable定义重复注解
     * 需要指定重复注解承载注解
     */
    public static void repeatableAnnotation(){
        Method[] declaredMethods = JDK8.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean annotationPresent = declaredMethod.isAnnotationPresent(Tests.class);
            if (annotationPresent) {
                Test[] annotationsByType = declaredMethod.getAnnotationsByType(Test.class);
                for (Test test : annotationsByType) {
                    System.out.println(test.type());
                }
            }
        }
    }
    @Repeatable(Tests.class)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Test {
        String type();
    };
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Tests {
        Test[] value();
    };

    /**
     * Base64编码支持
     */
    public static void base64(){
        String str = "mmmmm";
        String encoded = Base64.getEncoder().encodeToString(str.getBytes( StandardCharsets.UTF_8));
        //bW1tbW0=
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
    }

    /**
     * 使用元空间Metaspace代替持久代（PermGen space）
     * JVM参数使用-XX:MetaSpaceSize和-XX:MaxMetaspaceSize设置大小。
     */
    public static void jvmMetaSpace(){

    }
}
