package com.java.learn.interview;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class GetArrCompleteMaxValue {
    public static void main(String[] args) {
        int[] nums={15,20,9,6,99};
        String result=largeNumber(nums);
        System.out.println(result);

    }

    private static String largeNumber(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = nums.length-1; i >=0 ; i--) {
            int num = nums[i];
            int length=(num + "").length();
            List<Integer> integers = map.get(length);
            if (CollectionUtils.isEmpty(integers)){
                integers = new ArrayList<>();
            }
            integers.add(num);
            map.put(length,integers);
        }
        List<Integer> result = new ArrayList<>();
        map.entrySet().forEach(item->{
            result.addAll(item.getValue());
        });
        return Strings.join(result,',').replaceAll(",","");
    }
}
