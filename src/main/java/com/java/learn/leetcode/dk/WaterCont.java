package com.java.learn.leetcode.dk;

import java.util.LinkedList;
import java.util.Scanner;

public class WaterCont {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = Integer.parseInt(in.nextLine());
        int M = Integer.parseInt(in.nextLine());
        in.close();

        if (N < 3 || N > 7) {
            System.out.println(-1);
            return;
        }

        LinkedList<Integer> res = new LinkedList<>();

        int start = (int) Math.pow(10, N - 1);
        int end = (int) Math.pow(10, N);

        for (int i = start; i < end; i++) {
            int sum = 0;
            int bit = start;
            while (bit != 1) {
                sum += Math.pow(i / bit % 10, N);
                bit /= 10;
            }
            sum += Math.pow(i % 10, N);
            if (sum == i) {
                res.add(i);
            }
            if (res.size() == M + 1) {
                System.out.println(i);
                return;
            }
        }

        if (M > res.size()) {
            System.out.println(M * res.getLast());
        }


    }
}
