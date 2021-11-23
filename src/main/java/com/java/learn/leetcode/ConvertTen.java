package com.java.learn.leetcode;

import java.util.Scanner;

public class ConvertTen {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next();
            int res=Integer.decode(str);

            if(res<0||res>Math.pow(2,31)-1){
                continue;
            }
            System.out.println(res);
        }
    }
}
