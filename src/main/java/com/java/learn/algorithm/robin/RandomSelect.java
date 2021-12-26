package com.java.learn.algorithm.robin;

import java.util.Random;
//随机选取
public class RandomSelect implements ServerHandler {
    @Override
    public String getServer() {
        Random random = new Random();
        int rand = random.nextInt(ServerIps.LIST.size());
        return ServerIps.LIST.get(rand);
    }

}
