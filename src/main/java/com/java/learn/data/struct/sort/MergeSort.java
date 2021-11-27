package com.java.learn.data.struct.sort;

/**
 * 归并排序
 * 1945年冯 诺伊曼 提出
 * 归并排序原理：
 * 1.不断地将当前序列平均分割成2个子序列（直到不能再分割，序列中只剩一个元素）
 * 2.不断地将2个子序列合并成一个有序数列（直到最终只剩下1个有序数列）
 * 稳定性：稳定
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n/2+logn)=O(n) (n/2是临时存放数据   logn是因为递归调用)
 * T(n)=T(n/2)+T(n/2)+O(n)
 * T(1)=O(1)
 * T(n)/n=T(n/2)/(n/2)+O(1)
 * 令S(n)=T(n)/n
 * S(1)=O(1)
 * S(n)=S(n/2)+O(1)=S(n/4)+O(2)=S(n/8)+O(3)=S(n/2^k)+O(k)=S(1)+O(logn)=O(logn)
 * T(n)=n*S(n)=O(nlogn)
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围的数据进行归并排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;

        sort(begin, mid);

        sort(mid, end);

        merge(begin, mid, end);
    }

    /**
     * 将 [begin,mid) 和  [mid,end) 范围的序列合并成一个有序数列
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        //需要merge的2组序列存在于同一个数组中，并且是挨在一起的
        //为了更好地完成merge操作，最好将其中一组序列备份出来，比如[begin.mid)
        int li = 0, le = mid - begin;//左边数组  基于leftArray
        int ri = mid, re = end;//右边数组  基于array
        int ai = begin;//array的索引

        //备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        while (li < le) {//如果左边还没有结束
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];//拷贝右边数组到array
            } else {
                array[ai++] = leftArray[li++];//拷贝左边数组到array
            }
        }//cmp位置改为<=会失去稳定性
    }
}
