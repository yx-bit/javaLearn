package com.design.pattern.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlyWeight {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(TreeFactory.getTree("xxx", "xxxxx"), 0, 1);
        TreeNode treeNode1 = new TreeNode(TreeFactory.getTree("xxx", "xxxxx"), 1, 3);

    }
}

class TreeFactory {
    private static Map<String, Tree> map = new ConcurrentHashMap<>();

    public static Tree getTree(String name, String data) {
        if (map.containsKey(name)) {
            return map.get(name);
        }

        Tree tree = new Tree(name, data);
        map.put(name, tree);
        return tree;
    }
}
class TreeNode{
    private Tree tree;
    private int x;
    private int y;

    public TreeNode(Tree tree, int x, int y) {
        this.tree = tree;
        this.x = x;
        this.y = y;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
class Tree{
    //final  不可变  共享
    private final String name;
    private final String data;

    public Tree(String name, String data) {
        System.out.println("Tree create...."+name+"  tree created");
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}