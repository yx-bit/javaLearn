package com.java.learn.data.struct.sort;

/**
 * 基数排序
 *
 * 基数排序的原理：
 * 依次针对个位数、百位数、千位数。。。进行排序，从低位到高位
 * 个位数、十位数、百位数的取值范围都是固定的0-9，可以使用计数排序对他们进行排序
 *
 * 时间复杂度：O(d*(n+k)),d是最大值的位数，k是进制
 * 空间复杂度：O(n+k),k是进制
 * 稳定性：稳定
 */
public class RadixSort extends Sort<Integer>  {
    @Override
    protected void sort() {
        //空间复杂度O（kn+k）
        //时间复杂度O（dn）
        //d是最大值的位数，k是进制
        //找出最大值
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cmp(array[i],max) > 0) {
                max = array[i];
            }
        }
        //桶数组
        int[][] buckets=new int[10][array.length];
        //每个桶的元素数量
        int[] bucketSizes = new int[buckets.length];
        for (int divider = 1; divider <= max; divider*=10) {
            for (int i = 0; i < array.length; i++) {
                int no = array[i] / divider % 10;
                buckets[no][bucketSizes[no]++] = array[i];
            }
            int index = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < bucketSizes[i]; j++) {
                    array[index++] = buckets[i][j];
                }
                bucketSizes[i] = 0;
            }
        }
    }

    /**
     * 方式1
     */
    protected void sort0() {
        //找出最大值
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cmp(array[i],max) > 0) {
                max = array[i];
            }
        }
        for (int divider = 1; divider <= max; divider*=10) {
            countingSort0(divider);
        }
    }
    protected void countingSort0(int divider) {
        //开辟内存空间，存储次数
        int[] counts = new int[9 - 0 + 1];
        //统计每个个位数数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[(array[i]/divider%10)-0]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i]+=counts[i-1];
        }
        //从后往前遍历元素，将将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length-1; i >=0; i--) {
            //减1再覆盖值
            int index = --counts[(array[i]/divider%10) - 0];
            newArray[index]=array[i];
        }
        //将有序数组赋值到array
        for (int i = newArray.length-1; i >=0; i--) {
            array[i]=newArray[i];
        }
    }

}
