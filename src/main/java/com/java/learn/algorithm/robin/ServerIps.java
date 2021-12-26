package com.java.learn.algorithm.robin;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIps {
    //服务器列表
    public static final List<String> LIST= Arrays.asList(
            "A",
            "B",
            "C"
    );
    //带权重服务器列表
    public static final Map<String, Integer> WEIGHT_LIST = new LinkedHashMap<>();
    static {
        WEIGHT_LIST.put("A", 2);
        WEIGHT_LIST.put("B", 3);
        WEIGHT_LIST.put("C", 5);
    }
}
