package com.java.learn.leetcode.dk;

import java.util.Scanner;

public class Carspool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String next = scanner.next().replaceAll(",","");
            String[] split = next.split("[0]+");
            int count=0;
            for (String s : split) {
                int len = s.length();
                while(len>3){
                    count++;
                    len-=3;
                }
                if (len!=0){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
