package com.java.learn.data.struct.sort.cmp;

import com.java.learn.data.struct.sort.Sort;
import com.java.learn.util.Integers;
import com.java.learn.util.Times;

/**
 * 冒泡排序的几种方式
 * 冒泡排序的原理：
 * 从左到右依次比较，找到最符合条件的数据移到最后一位
 * 空间复杂度O（1）
 * 时间复杂度O（最坏的情况：n^2）
 * 稳定的算法
 */
public class BubbleSort<T extends Comparable> extends Sort {

    public static void main(String[] args) {
        Integer[] arr=Integers.tailAscOrder(1,10000,8000);
        //Integer[] arr=Integers.random(10000,1,20000);
        Integer[] arr1 = Integers.copy(arr);
        Integer[] arr2 = Integers.copy(arr);
        Integer[] arr3 = Integers.copy(arr);
        Integer[] arr4 = Integers.copy(arr);
        Times.test("method1", BubbleSort::method1,arr1);
        Times.test("method2", BubbleSort::method2,arr2);
        Times.test("method3", BubbleSort::method3,arr3);
        Times.test("method4", BubbleSort::method4,arr4);

    }


    //类尾部有序方式
    public static void method1(Integer[] integers) {
        int end = integers.length - 1;
        while (true) {
            if (end<=0){
                break;
            }
            int sorted=0;
            for (int begin = 0; begin < end; begin++) {
                if (integers[begin] > integers[begin + 1]) {
                    int tmp = integers[begin];
                    integers[begin] = integers[begin + 1];
                    integers[begin + 1] = tmp;
                    sorted=begin+1;
                }
            }
            end=sorted;
            end--;
        }
    }

    //双重for循环 外层通过end--控制每次长度减一（原因是每次冒泡排序完成最后一位都是最终的结果）
    public static void method2(Integer[] integers) {
        for (int end = integers.length - 1; end > 0; end--) {
            for (int begin = 0; begin < end; begin++) {
                if (integers[begin] > integers[begin + 1]) {
                    int tmp = integers[begin];
                    integers[begin] = integers[begin + 1];
                    integers[begin + 1] = tmp;
                }
            }
        }
    }

    //双重for循环通过sorted标记判断是不是天然有序
    private static void method3(Integer[] integers) {
        for (int end = integers.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 0; begin < end; begin++) {
                if (integers[begin] > integers[begin + 1]) {
                    int tmp = integers[begin];
                    integers[begin] = integers[begin + 1];
                    integers[begin + 1] = tmp;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    //双重for循环通过sortedIndex标记判断是不是范围天然有序(即序列尾部已经有序，可以记录最后依次交换的位置，减少比较次数)
    private static void method4(Integer[] integers) {
        for (int end = integers.length - 1; end > 0; end--) {
            int sortedIndex = 0;
            for (int begin = 0; begin < end; begin++) {
                if (integers[begin] > integers[begin + 1]) {
                    int tmp = integers[begin];
                    integers[begin] = integers[begin + 1];
                    integers[begin + 1] = tmp;
                    sortedIndex = begin + 1;
                }
            }
            end = sortedIndex;
        }
    }

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 0;
            for (int begin = 0; begin < end; begin++) {
                if (cmp(begin,begin + 1)>0) {
                    swap(begin,begin + 1);
                    sortedIndex = begin + 1;
                }
            }
            end = sortedIndex;
        }
    }
}
