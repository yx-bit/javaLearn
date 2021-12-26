package com.java.learn.algorithm.robin;

//轮询
public class RoundRobin implements ServerHandler {
    private static Integer pos=0;
    @Override
    public String getServer() {
        String ip=null;
        synchronized (pos) {
            if (pos >= ServerIps.LIST.size()) {
                pos = 0;
            }
            ip = ServerIps.LIST.get(pos);
            pos++;
        }
        return ip;
    }
}
