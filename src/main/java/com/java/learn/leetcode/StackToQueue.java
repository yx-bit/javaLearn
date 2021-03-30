package com.java.learn.leetcode;

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class StackToQueue {
    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    //将in数据挪到out
    private void transferIfEmpty() {
        if (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        transferIfEmpty();
        return out.pop();
    }
    public  int peek() {
        transferIfEmpty();
        return out.peek();
    }
    public boolean empty(){
        return in.empty() && out.empty();
    }

    public static void main(String[] args) {
        StackToQueue stackToQueue = new StackToQueue();
        boolean empty = stackToQueue.empty();
        System.out.println(empty);
        stackToQueue.push(3);
        System.out.println(stackToQueue.peek());//返回元素
        stackToQueue.push(1);
        stackToQueue.push(2);
        System.out.println(stackToQueue.pop());//弹出元素
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.peek());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.empty());
    }
}
