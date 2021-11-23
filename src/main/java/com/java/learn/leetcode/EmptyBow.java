package com.java.learn.leetcode;

import java.util.Scanner;

public class EmptyBow {
    /**
     * 1   0
     * 2   1
     * 3   1
     * 4   2
     * 5   2
     *
     */
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int next;
        while((next=scanner.nextInt())!=0){
            if (next%2!=0){
                System.out.println((next-1)/2);
            }else{
                System.out.println(next/2);
            }
        }
    }
}
