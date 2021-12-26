package com.java.learn.algorithm.robin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//缺点 权重大 ips越大 占内存  带权重随机
public class WeightRandom implements ServerHandler{
    //生成随机数作为List下标
    static List<String> ips = new ArrayList<>();
    static {
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            //weight多少 在ips里面存多少 例A 权重为2  在ips里存两个
            for (Integer i = 0; i < weight; i++) {
                ips.add(ip);
            }
        }
    }
    @Override
    public String getServer() {
        Random random = new Random();
        int randomPos = random.nextInt(ips.size());
        return ips.get(randomPos);
    }
}
