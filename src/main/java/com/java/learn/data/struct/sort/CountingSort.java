package com.java.learn.data.struct.sort;

import com.java.learn.data.struct.sort.Sort;

/**
 * 计数排序
 *  1954  seward  适合对一定范围内的整数进行排序
 * 计数排序的原理：
 * 统计每个整数在序列中出现的次数，进而推导出每个整数在有序数列中的索引
 *
 * 时间复杂度：O(n+k)   k是整数的取值范围
 * 空间复杂度：O(n+k)   k是整数的取值范围
 * 稳定排序
 *
 * 如果自定义对象可以提供用以排序的整数类型，依然可以使用计数排序
 */
public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //找出最值
        Integer max = array[0];
        Integer min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cmp(array[i],max) > 0) {
                max = array[i];
            }
            if (cmp(array[i],min) < 0) {
                min = array[i];
            }
        }
        //开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        //统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]-min]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i]+=counts[i-1];
        }
        //从后往前遍历元素，将将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length-1; i >=0; i--) {
            //减1再覆盖值
            int index = --counts[array[i] - min];
            newArray[index]=array[i];
        }
        //将有序数组赋值到array
        for (int i = newArray.length-1; i >=0; i--) {
            array[i]=newArray[i];
        }
    }

    /**
     * 最简单的实现
     * 无法对负整数进行排序
     * 极其浪费内存空间
     * 不稳定的排序
     */
    private void sortOriginVersion() {
        //找出最大值
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cmp(array[i],max) > 0) {
                max = array[i];
            }
        }
        //开辟内存空间，存储每个整数出现的次数
        int[] counts = new int[1 + max];
        //统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        //根据整数的出现次数，对整数进行排序
        int index=0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }
        }
    }
}
