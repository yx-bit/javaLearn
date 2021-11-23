package com.java.learn.leetcode.dk;

import java.util.Scanner;
import java.util.regex.Pattern;

public class DupCharCount {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String next = scanner.next();
            char[] chars = next.toCharArray();
            int max=0;
            for (int i = 0; i < chars.length; i++) {
                int temp=i;
                int len=1;
                while(temp+1<chars.length&&chars[temp]==chars[temp+1]){
                    len++;
                    temp++;
                }
                if (len>max)max=len;
            }
            System.out.println(max);
        }

    }
}
