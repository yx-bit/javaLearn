package com.java.learn.leetcode;

/**
 * 二分法查找数据
 */
public class TwoDivided {
    public static void main(String[] args) {
        int[] nums = new int[]{1,-10,6,15,7,-100,-9};
        int left = 0;
        int right = nums.length - 1;
        int count=1;
        int hidden=-9;
        while (left <= right) {
            System.out.println(count++);
            int ln=nums[left];
            int rn=nums[right];
            if (hidden == ln) {
                System.out.println("----找到数据了left"+left);
                break;
            }
            left++;
            if (hidden== rn) {
                System.out.println("----找到数据了right"+right);
                break;
            }
            right--;
        }
    }
}
