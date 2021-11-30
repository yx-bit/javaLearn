package com.java.learn.data.struct.sort.cmp;

import com.java.learn.data.struct.sort.Sort;

/**
 * 堆排序（可以认为是对选择排序的一种优化）
 * 堆是一类数据结构的统称，通常可以被看作一颗完全二叉树的数组对象
 * 堆排序原理：
 * 1.对序列进行原地建堆（heapify） 堆顶（第一个索引）为最大值
 * 2. 重复执行以下操作，直到堆的元素数量为1
 *  a.交换堆顶元素与尾元素
 *  b. 堆的元素数量减一
 *  c. 对0的位置进行1次siftDown操作
 *  时间复杂度：O(nlogN)
 *  空间复杂度：O(1)
 *  稳定性：不稳定
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;
    @Override
    protected void sort() {
        //原地建堆
        heapSize = array.length;
        for (int i = (heapSize>>1); i >=0;i--) {
            siftDown(i);
        }
        while(heapSize>1){
            //交换堆顶元素和尾部元素 并减一
            swap(0,--heapSize);
            //对0位置进行siftDown（恢复堆的性质）
            siftDown(0);
        }
    }
    private void heapify() {
        for (int i = (heapSize>>1); i >=0;i--) {
            siftDown(i);
        }
    }
    private void siftDown(int index) {
        T element= array[index];

        int half=heapSize>>1;
        while (index < half) {//index必须是非叶子节点
            //默认是左边跟父节点比
            int childIndex =(index<<1)+1;
            T child = array[childIndex];

            int rightIndex=childIndex+1;
            //右子节点比左子节点大
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }
            //大于等于子节点
            if (cmp(element,child)>=0) break;
            array[index]=child;
            index = childIndex;
        }
        array[index] = element;
    }

}
