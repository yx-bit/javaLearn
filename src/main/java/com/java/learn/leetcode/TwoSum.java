package com.java.learn.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1,-10,6,15,7,-100,-9};
        //int target = -9;
        //int[] ints = twoSum(nums, target);
        //System.out.println(Arrays.toString(ints));
    }

    //最优解法  时间复杂度O（n） 空间复杂度O（1）
    /*public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur=target - nums[i];
            if (map.containsKey(cur)) {
                return new int[]{map.get(cur),i};
            }
            map.put(nums[i], i);
        }
        return null;
    }*/
    //在哈希表2的基础上仍可改进，即每次在哈希表中搜索补数时，设置left和right两个指针，一次搜索nums中左右两端的元素的补数，可以得到耗时0ms的效果。
    //来源leetcode题解
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        if(nums == null || length < 2) {
            return new int[] {};
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left=0;
        int right = length - 1;
        while (left <= right) {
            int l = nums[left];
            int lv=target - l;
            int r = nums[right];
            int rv=target - r;
            if (map.containsKey(lv)) {
                return new int[]{left, map.get(lv)};
            }
            map.put(l, left++);
            if (map.containsKey(rv)) {
                return new int[]{right, map.get(rv)};
            }
            map.put(r, right--);
        }
        return new int[] {};
    }
    public static int[] twoSum3(int[] nums, int target) {
        if(nums == null || nums.length < 2) {
            return new int[] {};
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int ln0 = nums[left];
            int rn0 = nums[right];
            int ln1 = target - ln0;
            int rn1 = target - rn0;
            if(map.containsKey(ln1)) {
                return new int[] {left, map.get(ln1)};
            }
            else {
                map.put(ln0, left++);
            }
            if(map.containsKey(rn1)) {
                return new int[] {right, map.get(rn1)};
            }
            else {
                map.put(rn0, right--);
            }
        }
        return new int[] {};
    }
    //暴力解法  时间复杂度O（n~2） 空间复杂度O（1）
    /*public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            int cur=target - nums[i];
            for (int j = i+1; j < length; j++) {
                if (cur == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }*/
}
