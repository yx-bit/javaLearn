package com.java.learn.leetcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache {
    public static void main(String[] args) {
        List list = new ArrayList();
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        list.add(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        list.add(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        list.add(lRUCache.get(1));    // 返回 -1 (未找到)
        list.add(lRUCache.get(3));    // 返回 3
        list.add(lRUCache.get(4));    // 返回 4
        System.out.println(list);
    }

    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    //方式一 用jdk自带的LinkedHashMap特性（accessOrder设置为true 控制插入顺序+removeEldestEntry 控制容量）移除   运行结果  [1, -1, -1, 3, 4]
    //initialCapacity   初始容量大小，使用无参构造方法时，此值默认是16
    //loadFactor       加载因子，使用无参构造方法时，此值默认是 0.75f
    //accessOrder   false： 基于插入顺序     true：  基于访问顺序  get一个元素后，这个元素被加到最后(使用了LRU 最近最少被使用的调度算法)
    //重写removeEldestEntry 如果缓存大小大于容量大小则返回true，即执行putVal.afterNodeInsertion.removeEldestEntry.removeNode(first.key)方法中把第一个值删掉（get时会进行LRU顺序调整）
    /*public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer,Integer>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return cache.size()>capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }*/
    //方式二 自己实现get时的LRU算法  由LinkedHashMap的removeEldestEntry来保证移除第一个元素（或者在put时自己判断进行移除）
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return cache.size() > capacity;
            }
        };
    }

    public int get(int key) {
        Integer value = cache.getOrDefault(key, -1);
        if (value != -1) {
            cache.remove(key);
            put(key, value);
        }
        return value;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
