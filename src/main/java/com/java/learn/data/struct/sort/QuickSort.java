package com.java.learn.data.struct.sort;

/**
 * 快速排序
 * 1960年  东尼  霍尔
 * 快速排序原理（本质是逐渐将每一个元素都转换成轴点元素）：
 * 1.从序列中选择一个轴点元素（pivot）
 * 假设每次选择0位置的元素为轴点元素
 * 2.利用pivot将序列分割成2个子序列
 * 将小于pivot的元素放在pivot前面（左侧）
 * 将大于pivot的元素放在pivot后面（右侧）
 * 等于pivot的元素放哪边都可以
 * 3.对子序列进行1、2操作
 * 直到不能再分割（子序列中只剩下1个元素）
 * 时间复杂度：
 * 最好情况：T(n)=2*T(n/2)+O(n)=O(nlogn)
 * 最坏情况：T(n)=T(n-1)+O(n)=O(n^2)
 * 为了降低最坏情况的概率，一般采取的做法是：
 * 随机选择轴点元素
 * 空间复杂度：
 * 由于递归调用，O（logn）
 * 稳定性：不稳定
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围的元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {//T(n)=2*T(n/2)+O(n)=O(nlogn)
        if (end - begin < 2) {
            return;
        }
        //确定轴点位置O(n)
        int mid = pivotIndex(begin, end);
        //对子序列进行快速排序
        sort(begin, mid);//T(n/2)

        sort(mid + 1, end);//T(n/2)
    }

    /**
     * 构造出[begin,end)范围的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        //随机选择一个元素跟begin位置交换  优化最坏情况
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        //备份begin位置的元素
        T pivot = array[begin];
        //end指向最后一个元素
        end--;

        while (begin < end) {
            while (begin < end) {
                //如果采用<=0 会出现轴点元素分割出来的子序列分布不均匀 导致最坏时间复杂度 导致stackoverflow
                if (cmp(pivot, array[end]) < 0) {//右边元素大于轴点元素
                    end--;
                } else {//右边元素小于等于轴点元素
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                //如果采用<=0 会出现轴点元素分割出来的子序列分布不均匀 导致最坏时间复杂度 导致stackoverflow
                if (cmp(pivot, array[begin]) > 0) {//左边元素小于轴点元素
                    begin++;
                } else {
                    array[end] = array[begin];
                    end--;
                    break;
                }
            }
        }

        //将轴点元素放入最终的位置
        array[begin] = pivot;
        //返回轴点位置元素
        return begin;

    }
}
