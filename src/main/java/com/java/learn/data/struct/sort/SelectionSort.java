package com.java.learn.data.struct.sort;

/**
 * 选择排序：
 * 找出最大的元素，与最末尾的元素交换位置
 * 时间复杂度 O(n^2)
 * 空间复杂度O(n)
 * 稳定性：不稳定（7 5 10 10 2 4 2）
 *                7 5 10 2 2 4 10  （2的下标出现了不稳定的情况）
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length-1; end >0 ; end--) {
            int maxIndex=0;
            for (int begin = 0; begin <= end; begin++) {
                if (cmp(maxIndex,begin)<=0){
                    maxIndex=begin;
                }
            }
            swap(maxIndex,end);
        }

    }
}
