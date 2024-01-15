package com.java.learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestMain {
    public static void main(String[] args) {
//        1\实现一个字符串反转函数。
        System.out.println(reverse("acdde"));
//        2\实现一个数组去重函数。
        System.out.println(Arrays.toString(distinct(new Integer[]{1, 3, 5, 6, 5, 7})));
//        3\实现一个简单的搜索算法，如二分搜索。
        System.out.println(binarySearch(10,new int[]{1,3,6,8,10}));
        Node node=new Node(1);
        node.next=new Node(2);
        node.next.next=new Node(3);
        node.next.next.next=new Node(4);
        node.next.next.next.next=new Node(5);
        System.out.println(reverse(node));
    }
    @Data
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value=value;
        }

    }
    public static Node reverse(Node node) {
        if (node == null) {
            return node;
        }
        Node pre=null;
        Node next=null;
        while (node != null) {
            next=node.next;
            node.next=pre;//实现了反转
            pre=node;//保存反转结果
            node=next;

        }
        return pre;
    }
    // 1\实现一个字符串反转函数。
    public static String reverse(String str) {
        if (str==null||str.isEmpty()) {
            return str;
        }
        char[] chars = str.toCharArray();
        int start=0;
        int end=chars.length-1;
        while (start < end) {
            char temp=chars[start];
            chars[start]=chars[end];
            chars[end]=temp;
            start++;
            end--;
        }
        return new String(chars);
    }

    //2\实现一个数组去重函数。
    public static Integer[] distinct(Integer[] arr) {
        if (arr == null) {
            return arr;
        }
        Set<Integer> dataSet = new HashSet<>();
        Integer[] temp={};
        for (int i = 0; i < arr.length; i++) {
            dataSet.add(arr[i]);
        }
        return dataSet.toArray(temp);
    }

    //3\实现一个简单的搜索算法，如二分搜索。
    public static int binarySearch(int key,int[] arr) {
        if (arr == null) {
            return -1;
        }
        //对arr进行排序
        //Arrays.sort(arr);
        int begin=0;
        int end=arr.length;
        while (begin < end) {
            int mid=(begin+end)/2;
            if (key == arr[mid]) {
                return mid;
            }
            if (key < arr[mid]) {
                end=mid;
                continue;
            }
            if (key > arr[mid]) {
                begin=mid+1;
                continue;
            }

            return mid;
        }
        return -1;
       }
}
