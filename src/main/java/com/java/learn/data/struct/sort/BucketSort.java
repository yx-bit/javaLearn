package com.java.learn.data.struct.sort;


import java.util.LinkedList;
import java.util.List;

/**
 * 桶排序
 *
 * 桶排序的原理
 * 1.创建一定数量的桶（比如用数组、链表作为桶）
 * 2.按照一定的规则（不同类型的数据、规则不同），将序列中的元素均匀分配到对应的桶
 * 3.分别对每个桶进行单独排序
 * 4.将所有非空桶的元素合并成有序数列
 *
 * 元素再桶中的索引
 * 元素值*元素数量
 *
 * 空间复杂度：O(n+m)   m 桶的数量
 * 时间复杂度：O(n)+m*O(n/m+log(n/m))=O(n+n*log(n/m))=O(n+n*logn-n*logm)
 * 稳定性：
 */
public class BucketSort extends Sort<Integer> {
    @Override
    protected void sort() {
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cmp(array[i],max) > 0) {
                max = array[i];
            }
        }
        //1000   0.001   1*10^-3
        //100   0.01
        //10   0.1
        //1    1
        double pow = Math.pow(10, -(max + "").length());
        //桶数组
        List<Integer>[] buckets = new List[array.length];
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (int) (array[i] * pow);
            List<Integer> bucket = buckets[bucketIndex];
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }
            bucket.add(array[i]);
        }
        //对每个桶进行排序
        int index=0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i]==null) continue;
            buckets[i].sort(null);
            for (Integer d : buckets[i]) {
                array[index++] = d;
            }
        }
    }
}
