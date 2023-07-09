package com.java.learn.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;



public class ReverseList {



    @Data
    @AllArgsConstructor
    static class ListNode<T>{
        private T value;
        private ListNode<T> next;
    }

    public static void main(String[] args) {
        ListNode<Integer> node5 = new ListNode<>(5, null);
        ListNode<Integer> node4 = new ListNode<>(4, node5);
        ListNode<Integer> node3 = new ListNode<>(3, node4);
        ListNode<Integer> node2 = new ListNode<>(2, node3);
        ListNode<Integer> node1 = new ListNode<>(1, node2);

        System.out.println(node1);
        //迭代反转
        //ListNode<Integer>  iterateNode=iterate(node1);
        //System.out.println(iterateNode);
        System.out.println("----------------------");
        //递归反转
        ListNode<Integer>  recursiveNode=recursive(node1);
        System.out.println(recursiveNode);
    }


    /**
     * 递归方法的原理：
     * 1. 创建一个递归函数，将当前节点作为参数。
     * 2. 检查当前节点或下一个节点是否为null。如果是，则将当前节点作为反转后链表的头节点返回。
     * 3. 递归调用函数，将下一个节点作为参数。
     * 4. 将下一个节点的下一个指针设置为当前节点，实现链表的反转。
     * 5. 将当前节点的下一个指针设置为null，打破原始的链接。
     * 6. 返回反转后链表的头节点，即递归函数的返回结果。
     * @param node1
     * @return
     */
    private static ListNode<Integer> recursive(ListNode<Integer> node1) {
        if (node1 == null || node1.next == null) {
            return node1;
        }
        ListNode<Integer> next = node1.next;
        node1.next=null;
        ListNode<Integer> recursiveNode = recursive(next);
        next.next = node1;
        return recursiveNode;
    }

    /**
     *  迭代方法的原理：
     * 1. 初始化三个指针：prev（前一个节点）、current（当前节点）和next（下一个节点）。将prev设为null，将current设为链表的头节点，将next设为null。
     * 2. 循环遍历链表，直到current指针变为null。
     * 3. 在每次循环中：
     *    a. 将当前节点的下一个节点存储在next指针中。
     *    b. 将当前节点的下一个节点更新为前一个节点，从而反转链表。
     *    c. 将prev指针移到当前节点。
     *    d. 将current指针移到下一个节点。
     * 4. 循环结束后，prev指针将指向反转后链表的头节点。
     * 5. 将prev指针作为反转后链表的头节点返回。
     * 1 - 2  3  4  5
     * 5  4  3  2  1
     *
     * @param node1
     * @return
     */
    private static ListNode<Integer>  iterate(ListNode<Integer> node1) {
        ListNode<Integer> prev=null,current=node1,next=null;
        while (current != null) {
            next = current.next;
            current.next=prev;
            //转换完毕  准备下一次初始数据
            prev=current;
            current=next;
        }
        return prev;
    }

}
