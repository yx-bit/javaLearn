package com.java.learn.leetcode.dk;

import java.util.LinkedList;

public class ClearDup {
    public static void main(String[] args) {
        String str="mMbccbcddc";
        LinkedList<Character> list = new LinkedList<>();

        for (char c:str.toCharArray()){
            list.add(c);
        }
        int count=0;
        while (list.size() != count) {
            count = list.size();
            for (int i=0;i<list.size()-1;i++){
                if (list.get(i)==list.get(i+1)){
                    list.remove(i);
                    list.remove(i);
                    i--;
                }
            }
        }
        System.out.println(list.size());
    }
}
