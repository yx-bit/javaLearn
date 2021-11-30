package com.java.learn.data.struct.sort.cmp;

/**
 * 二分搜索
 *
 */
public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int[]{2,4,6,8,10},8));
    }
    public static int indexOf(int[] array,int v){
        int begin=0;
        int end = array.length;
        while (begin < end) {
            int mid=(begin+end)>>1;//除2==右移1
            if (v<array[mid]){
                end=mid;
                continue;
            }
            if (v>array[mid]){
                begin=mid+1;
                continue;
            }
            return mid;
        }
        return -1;
    }

    /**
     * 查找v在有序算法中的插入位置
     * @param array
     * @param v
     * @return
     */
    public static int search(int[] array,int v){
        //要求二分搜索返回的插入位置：第一个大于v的元素位置
        int begin=0;
        int end = array.length;
        while (begin < end) {
            int mid=(begin+end)>>1;//除2==右移1
            if (v<array[mid]){
                end=mid;
                continue;
            }
            if (v>=array[mid]){
                begin=mid+1;
                continue;
            }
        }
        return end;
    }
}
