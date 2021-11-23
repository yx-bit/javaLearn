package com.java.learn.leetcode;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class MingRandom {
    public static void main(String[] args){

        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            int s=scan.nextInt();
            if(s<1||s>1000){
                continue;
            }
            TreeSet<Integer> treeSet=new TreeSet<Integer>();
            boolean flag=false;
            for(int i=0;i<s;i++){
                int val=scan.nextInt();
                if(val<1||val>500){
                    flag=true;
                    break;
                }
                treeSet.add(val);
            }
            if(flag){
                continue;
            }
            treeSet.forEach(System.out::println);
        }
    }
}
