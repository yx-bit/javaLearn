package com.java.learn.interview;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Tree {
    public static void main(String[] args) {
        Tree rootTree = new Tree();
        Tree firstTree = new Tree();
        rootTree.setTree(firstTree);
        Tree secondTree = new Tree();
        firstTree.setTree(secondTree);
        System.out.println(JSON.toJSONString(rootTree));
        Map<String, Map> rootMap = new HashMap<>();
        Map<String, Map> map = new HashMap<>();
        Map<String, Map> map1 = new HashMap<>();
        Map<String, Map> map2 = new HashMap<>();
        Map<String, Map> map3 = new HashMap<>();
        Map<String, Map> map11= new HashMap<>();

        map2.put("map3", map3);
        map1.put("map2", map2);
        map.put("map1", map1);
        map.put("map11", map11);
        rootMap.put("root", map);
        System.out.println(JSON.toJSONString(rootMap));
    }
    private Long id;
    private String name;
    private String value;
    private String url;
    private Tree tree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
