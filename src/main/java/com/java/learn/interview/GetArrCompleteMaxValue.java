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
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStrings, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
            return ba.compareTo(ab);
        });

        StringBuilder result = new StringBuilder();
        for (String numString : numStrings) {
            result.append(numString);
        }

        return result.toString();
    }
}
