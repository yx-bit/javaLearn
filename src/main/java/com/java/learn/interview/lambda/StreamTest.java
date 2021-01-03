package com.java.learn.interview.lambda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
     static List<Integer> integers;
     static List<Person> lists = new ArrayList<>();

    public StreamTest() {
    }

    static{
        integers= Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        lists.add(new Person("Tom", 8900,20, "male", "New York"));
        lists.add(new Person("Jack", 7000,21, "male", "Washington"));
        lists.add(new Person("Lily", 7800,18, "female", "Washington"));
        lists.add(new Person("Anni", 8200,25, "female", "New York"));
        lists.add(new Person("Owen", 9500,20, "male", "New York"));
        lists.add(new Person("Alisa", 7900,20, "female", "New York"));

    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        //遍历
        System.out.println(foreach());
        //遍历匹配
        System.out.println(findFirst());
        System.out.println(findAny());
        System.out.println(anyMatch());
        //筛选
        System.out.println(filter());
        //聚合
        System.out.println(max());
        System.out.println(min());
        System.out.println(count());
        //映射
        System.out.println(map());
        System.out.println(flatMap());
        //归约
        System.out.println(reduce());
        //收集 collect


    }
    //遍历
    public static List foreach() {
        return integers.stream().filter(x -> x > 6).collect(Collectors.toList());
    }
    //匹配第一个
    public static Optional<Integer> findFirst() {
        return integers.stream().filter(x -> x > 6).findFirst();
    }

    //匹配任意(适用于并行流)
    public static Optional<Integer> findAny() {
        return integers.stream().filter(x -> x > 6).findAny();
    }
    //匹配符合特定条件的值
    public static boolean anyMatch() {
        return integers.stream().anyMatch(x -> x > 6);
    }
    //匹配工资高于8000的员工姓名
    public static List<String> filter(){
        return lists.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
    }
    //找到工资最高的人
    public static Optional<Person> max() {
        return lists.stream().max(Comparator.comparingInt(Person::getSalary));
    }
    //统计工资大于8000的人数
    public static long count() {
        return lists.stream().filter(x -> x.getSalary() > 8000).count();
    }
    //找到工资最低的人
    public static Optional<Person> min() {
        return lists.stream().min(Comparator.comparingInt(Person::getSalary));
    }
    //找到工资最低的人并加100元
    public static Person map() {
        return lists.stream().min(Comparator.comparingInt(Person::getSalary)).
                map(x->{x.setSalary(x.getSalary()+500 );return x;}).get();
    }
    //流中组合集合
    public static List flatMap() {
        List<String> strings = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> collect = strings.stream().flatMap(s -> {
            String[] split = s.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        return collect;
    }
    //求所有人工资之和
    public static Optional reduce() {
        return lists.stream().map(Person::getSalary).reduce(Integer::sum);
    }
}
