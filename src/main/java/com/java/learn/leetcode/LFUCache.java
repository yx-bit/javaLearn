package com.java.learn.leetcode;

public class LFUCache {

    
    static class Node<T>{
        private int count;
        private T value;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
