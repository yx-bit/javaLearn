package com.java.learn.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class QueueToStack {
    private Queue<Integer> in = new LinkedList<>();
    private Queue<Integer> out = new LinkedList<>();

    public void push(int x) {
        in.offer(x);
        while (!out.isEmpty()) {
            in.offer(out.poll());
        }
        Queue temp=in;
        in=out;
        out=temp;
    }

    public int pop() {
        return out.poll();
    }
    public boolean empty() {
        return out.isEmpty();
    }

    public static void main(String[] args) {
        QueueToStack queueToStack = new QueueToStack();
        System.out.println(queueToStack.empty());
        queueToStack.push(1);
        queueToStack.push(2);
        queueToStack.push(3);
        System.out.println(queueToStack.pop());
        queueToStack.push(4);
        System.out.println(queueToStack.pop());
        System.out.println(queueToStack.pop());
        System.out.println(queueToStack.pop());
        System.out.println(queueToStack.pop());
        System.out.println(queueToStack.empty());
    }
}
