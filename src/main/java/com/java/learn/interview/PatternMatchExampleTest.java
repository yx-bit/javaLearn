package com.java.learn.interview;

import java.util.HashMap;
import java.util.Map;

public class PatternMatchExampleTest {

    public static void main(String[] args) {
        System.out.println(patternMatch("背景 上海 青岛 青岛", "abcc"));
        System.out.println(patternMatch("背景 青岛 上海 青岛", "abab"));
    }

    public static boolean patternMatch(String str, String pattern) {
        String[] split = str.split(" ");
        if (split.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            char key = pattern.charAt(i);
            if (map.containsKey(key) && !map.get(key).equals(split[i])) {
                return false;
            }
            map.put(key, split[i]);
        }
        return true;
    }
}
