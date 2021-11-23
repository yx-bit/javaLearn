package com.java.learn.leetcode;

import java.util.Scanner;

public class IntBinaryCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int s = scanner.nextInt();
            int count = 0;
            String e = Integer.toBinaryString(s);
            for (int i = 0; i < e.length(); i++) {
                if (e.charAt(i) == '1') {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
