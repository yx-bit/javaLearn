package com.java.learn.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    public static void test(){
        for (int i = 0; i < 100 ; i++) {
            int rightOne=i&(~i+1);
            System.out.println(i+":"+rightOne);
        }

    }

    public static void main(String[] args) {
        boolean matches = Pattern.matches("^.*\\W.*$", "!we134");
        System.out.println(matches);
        String[] s="xxyyyXXYY".split("");
        Arrays.sort(s);
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character s1 : "xxyyyXXYY".toCharArray()) {
            map.put(s1,map.getOrDefault(s1,0)+1);
        }
        Map<Character, Integer> collect = map.entrySet().stream().filter(entry -> entry.getKey() > 'a').collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(collect.entrySet());
        list.sort(Comparator.comparing(Map.Entry<Character,Integer>::getValue).reversed());
        Map<Character, Integer> collect1 = map.entrySet().stream().filter(entry -> entry.getKey() < 'Z').collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
        List<Map.Entry<Character,Integer>> list1 = new ArrayList<>(collect1.entrySet());
        list1.sort(Comparator.comparing(Map.Entry<Character,Integer>::getValue).reversed());
        list.addAll(list1);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Integer> characterIntegerEntry : list) {
            stringBuilder.append(characterIntegerEntry.getKey());
            stringBuilder.append(":");
            stringBuilder.append(characterIntegerEntry.getValue());
            stringBuilder.append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder.toString());
        System.out.println(Arrays.toString(s));
        System.out.println((int)'a');
        System.out.println((int)'A');
        System.out.println((int)'z');
        System.out.println((int)'Z');
        System.out.println(1%10);
    }
}
