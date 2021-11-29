package com.java.learn.data.struct.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1959 希尔
 * 希尔排序
 * <p>
 * 希尔排序的原理：
 * 希尔排序把序列看作一个矩阵，分成m列，逐列进行排序
 * m从整个整数逐渐减1
 * 当m为1时，整个序列将完全有序
 * 因此，希尔排序也被称为递减增量排序
 * 矩阵的列数取决于步长序列（希尔本人给出的步长序列是n/2^k）
 * 例如{1，2，，4，8，16，32，64}
 * 不同的步长序列执行效率不同
 * 从多列变为1列的过程中，逆序对的数量在逐渐减少
 * 因此希尔排序底层一般使用插入排序对每一列进行排序
 * 时间复杂度：
 * 最坏情况：希尔本人给出的步长序列算法是O（n^2）
 * 最坏情况：1986 Sedgewick  O(n^4/3)
 * 9( 2^k -2^k/2 )+1         k  even
 * 8*2^k - 6*2^(k+1)/2  +1   k odd
 * 最好情况：O(n)
 * 空间复杂度：O（1）
 * 稳定性：不稳定
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence(array.length);
        //List<Integer> stepSequence = sedgewickStepSequence(array.length);
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 分成step列进行排序
     *
     * @param step
     */
    private void sort(int step) {
        //col:第几列

        for (int col = 0; col < step; col++) {//对第col列进行排序
            //假设元素在第col列、第row行，步长（总列数）是step
            //那么这个元素在数组中的索引是  col+row*step
            for (int begin = col+step; begin < array.length; begin+=step) {//对[0,array.length）进行插入排序
                int cur = begin;
                T v = array[cur];
                while (cur > col && cmp(v, array[cur - step]) <= 0) {
                    array[cur] = array[cur - step];
                    cur-=step;
                }
                array[cur] = v;
            }
        }
    }

    /**
     * 希尔步长算法
     * n/2^k
     *
     * @return
     */
    private List<Integer> shellStepSequence(int count) {
        List<Integer> stepSequence = new ArrayList<>();

        while ((count >>= 1) > 0) {
            stepSequence.add(count);
        }
        return stepSequence;
    }

    /**
     * sedgewick步长算法
     * 9( 2^k -2^k/2 )+1         k  even
     * 8*2^k - 6*2^(k+1)/2  +1   k odd
     * @return
     */
    private List<Integer> sedgewickStepSequence(int count) {
        List<Integer> stepSequence = new LinkedList<>();
        int k=0,step=0;
        while (step < count) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            }else{
                int pow1 = (int) Math.pow(2, (k-1) >> 1);
                int pow2 = (int) Math.pow(2, (k+1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}

