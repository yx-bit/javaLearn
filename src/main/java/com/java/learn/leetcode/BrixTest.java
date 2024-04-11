package com.java.learn.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Stack;

public class BrixTest {

    public static void main(String[] args) {
        //使用java实现一段程序，要求输入数组[1,0,1,2,0,1,3]返回[1,1,2,1,3,0,0]；
        System.out.println(Arrays.toString(moveZero(new int[]{1, 0, 1, 2, 0, 1, 3})));
/*
        使用java编写密码验证函数，符合如下所有条件返回strong，否则返回weak，条件如下：
            1.判断同一个字符有没有出现3次
            2.密码的长度必须在8到22个字符之间
            3.密码必须至少包含一个小写字母 一个大写字母和一个数字
*/
        System.out.println(verifyPassWord("1033432,?aA"));

        //使用java写一段程序检查输入的字符串是否为一对一的圆括号开闭关系，如果是返回true，如果不是返回false，输入字符串限制为0到100
        System.out.println(judageCircleComplete("(()())"));

        //链表反转
        System.out.println(reverseNode(new ListNode(1, new ListNode<>(2, new ListNode<>(3, null)))));

        System.out.println(reverseNodeByStack(new ListNode(1, new ListNode<>(2, new ListNode<>(3, null)))));
    }

    // node childNode childNode node
    private static ListNode reverseNode(ListNode cur) {
        ListNode<Integer> prev=null;
        while (cur != null) {
            ListNode<Integer> temp=cur.next;//下一节点临时存放
            cur.next=prev;//反转下一节点
            prev=cur;//赋值当前节点
            cur=temp;//下一节点继续遍历
        }
        return prev;
    }
    public static ListNode reverseNodeByStack(ListNode cur){
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            ListNode temp=cur.next;
            cur.next=null;
            stack.push(cur);
            cur=temp;
        }
        if (stack.isEmpty()) {
            return cur;
        }
        // 弹出栈中的节点重新连接它们
        ListNode reversedHead = stack.pop();
        cur = reversedHead;//可以改变cur而不改变reverseHead
        while (!stack.isEmpty()) {
            ListNode nextNode = stack.pop();
            cur.next = nextNode;
            cur = nextNode;
        }
        cur = null;

        return reversedHead;
    }
    @Data
    @AllArgsConstructor
    static class ListNode<T>{
        private T value;
        private ListNode<T> next;
    }
    private static String verifyPassWord(String str) {
        if (Strings.isBlank(str) || str.length() < 8 || str.length() > 22) {
            return "weak";
        }
        if (!str.matches(".*[a-z].*")) {
            return "weak";
        }
        if (!str.matches(".*[A-Z].*")) {
            return "weak";
        }
        if (!str.matches(".*\\d.*")) {
            return "weak";
        }
        if (!str.matches(".*[!@#$%^&*(),.?\":{}|<>;']{2,}.*")) {
            return "weak";
        }
        for (int i = 0; i < str.length()-1; i++) {
            if (str.charAt(i) == str.charAt(i + 1) &&
                    str.charAt(i) == str.charAt(i + 2)) {
                return "weak";
            }
        }

        return "strong";
    }

    private static boolean judageCircleComplete(String str) {
        if (Strings.isBlank(str)) {
            return true;
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('('==ch) {
                stack.push(ch);
            }
            if (')'==ch) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static int[] moveZero(int[] arr) {
        if (arr == null) {
            return arr;
        }
        int n= arr.length;
        int j=0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                if (i != j) {
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
                j++;
            }
        }
        return arr;
    }


}
