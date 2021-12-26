package com.java.learn.algorithm.robin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//带权重随机
public class WeightRandomV2 implements ServerHandler{

    @Override
    public String getServer() {
        //总权重
        int totalWeights = ServerIps.WEIGHT_LIST.values().stream().mapToInt(i -> i).sum();

        Random random = new Random();
        int pos = random.nextInt(totalWeights);
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            if (pos < weight) {
                return ip;
            }
            pos = pos - weight;
        }
        return null;
    }
}
