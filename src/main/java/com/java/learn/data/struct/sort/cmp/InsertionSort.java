package com.java.learn.data.struct.sort.cmp;

import com.java.learn.data.struct.sort.Sort;

/**
 * 插入排序
 * 插入排序的原理：
 * 1.在执行过程中，插入排序会将序列分为2部分
 *  （头部是已经排好序的，尾部是待排序的）
 * 2.从头开始扫描每一个元素
 * 每当扫描到一个元素，就将他插入到头部合适的位置，使得头部数据依然保持有序
 * 优化：
 * 将交换转为挪动
 * 1.先将待插入的元素备份
 * 2.头部有序数据中比待插入元素大的，都朝尾部方向挪动1个位置
 * 3.将待插入的元素放到最终合适的位置
 * 时间复杂度：最坏：O（n^2） 最好：O（n）（逆序对（9 8 7 6 5 如果是按从小到大排序，逆序对为9 8、9 7。。。最多）的数量越多，时间复杂度越高）
 * 空间复杂度：O（1）
 * 属于稳定排序
 */
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            //原始方式
            //int cur=begin;
            //while (cur>0&&cmp(cur,cur-1)<=0){
            //    swap(cur,cur-1);
            //    cur--;
            //}
            //优化方式:将交换转为挪动
            //int cur=begin;
            //T v=array[cur];
            //while (cur>0&&cmp(v,array[cur-1])<=0){
            //    array[cur]=array[cur-1];
            //    cur--;
            //}
            //array[cur]=v;
            //优化方式:二分搜索 （不稳定）
            T v=array[begin];
            int insertIndex = search(begin);
            //将[insertIndex,begin)范围内的元素往右边挪动一个单位
            for (int i = begin; i > insertIndex; i--) {
                array[begin]=array[begin-1];
            }
            array[insertIndex]=v;
        }
    }

    /**
     * 利用二分搜索找到index位置元素的待插入位置
     * 已经排好序的区间范围是[0,index）
     * @param index
     * @return
     */
    private int search(int index){
        T v = array[index];
        int begin=0;
        int end = index;
        while (begin < end) {
            int mid=(begin+end)>>1;//除2==右移1
            if (cmp(v,array[mid])<0){
                end=mid;
                continue;
            }
            if (cmp(v,array[mid])>=0){
                begin=mid+1;
                continue;
            }
        }
        return end;
    }
}
