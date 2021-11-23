package com.java.learn.interview;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class OddEvenOrderAndPrint {
    public static void main(String[] args) {
        int[] nums={4,8,5,6,7,1,14,2};
        String s= evenOddOrder(nums);
        System.out.println(s);//14,7,8,5,6,1,4,2
    }
    private static String evenOddOrder(int[] nums) {
        int length=nums.length;
        Arrays.sort(nums);
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i = nums.length-1; i >=0; i--) {
            int num = nums[i]%2;
            if (num==0){
                evenList.add(nums[i]);
            }else{
                oddList.add(nums[i]);
            }
        }
        int oddSize = oddList.size();
        int evenSize = evenList.size();
        for (int i = 0; i < length; i++) {
            if (i<=evenSize-1){
                resultList.add(evenList.get(i));
            }
            if (i<=oddSize-1){
                resultList.add(oddList.get(i));
            }
        }
        return Strings.join(resultList, ',');
    }
}
