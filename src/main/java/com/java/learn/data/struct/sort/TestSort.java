package com.java.learn.data.struct.sort;

import com.java.learn.util.Integers;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        //Integer[] arr= Integers.tailAscOrder(1,10000,8000);
        Integer[] arr=Integers.random(10000,1,200000);
        testSort(arr,new QuickSort(),new MergeSort(),new BubbleSort<Integer>(),new SelectionSort<Integer>(),new HeapSort<Integer>(),new InsertionSort<Integer>());
    }
    public static <T extends Comparable>void testSort(T[] array, Sort... sorts){
        for (Sort sort : sorts) {
            Integer[] copy = Integers.copy((Integer[]) array);
            sort.sort(copy);
        }
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
