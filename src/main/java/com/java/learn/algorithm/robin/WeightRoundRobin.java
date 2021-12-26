package com.java.learn.algorithm.robin;

import java.util.HashMap;
import java.util.Map;

public class WeightRoundRobin implements ServerHandler {
    public static Map<String, Weight> currWeights = new HashMap<>();
    @Override
    public String getServer() {
        //总权重
        int totalWeights = ServerIps.WEIGHT_LIST.values().stream().mapToInt(i -> i).sum();
        //currentWeight 默认值0
        if (currWeights.isEmpty()) {
            ServerIps.WEIGHT_LIST.forEach((ip,weight)->{
                currWeights.put(ip, new Weight(ip, weight, 0));
            });
        }
        for (Weight weight : currWeights.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight()+weight.getWeight());
        }
        //找最大值
        Weight maxCurrentWeight = null;
        for (Weight weight : currWeights.values()) {
            if (maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()) {
                maxCurrentWeight = weight;
            }
        }
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeights);
        return maxCurrentWeight.getIp();
    }
}
