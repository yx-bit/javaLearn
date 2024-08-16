package com.java.learn.leetcode;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{1,-10,9,5,6,15,7,-100,-9};

        //quickSort(nums, 0, nums.length - 1);
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partition = partition(nums, left, right);
            quickSort(nums, left, partition - 1);
            quickSort(nums, partition + 1, right);
        }
    }
    static int partition(int[] nums, int left, int right){
        int pivot = nums[right];
        int cur=left;
        for (; left < nums.length; left++) {
            if (nums[left] < pivot) {
                swap(nums, cur++, left);
            }
        }
        if (nums[cur] > pivot)
        swap(nums, cur, right);
        return cur;
    }

    static void swap(int[] arrs, int l, int r) {
        int temp=arrs[l];
        arrs[l]=arrs[r];
        arrs[r]=temp;
    }
}
