package com.java.learn.algorithm.robin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    static Map<String, ServerHandler> robin = new ConcurrentHashMap<>();
    static {
        List<Class<? extends ServerHandler>> classes = Arrays.asList(
                RandomSelect.class,
                RoundRobin.class,
                WeightRandomV2.class,
                WeightRoundRobin.class,
                WeightRandom.class
        );
        classes.forEach(item->{
            try {
                setHandler(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    public static void setHandler(Class<? extends ServerHandler> clazz) throws IllegalAccessException, InstantiationException {
        robin.put(clazz.getName(), clazz.newInstance());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //System.out.println(robin.get(RandomSelect.class.getName()).getServer());
            //System.out.println(robin.get(RoundRobin.class.getName()).getServer());
            //System.out.println(robin.get(WeightRandom.class.getName()).getServer());
            //System.out.println(robin.get(WeightRandomV2.class.getName()).getServer());
            System.out.println(robin.get(WeightRoundRobin.class.getName()).getServer());
        }
    }
}
