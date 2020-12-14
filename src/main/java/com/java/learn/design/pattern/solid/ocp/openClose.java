package com.java.learn.design.pattern.solid.ocp;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//策略模式实例
public class openClose {
    public static void main(String[] args) {
        Strategy strategy = StrategyFactory.getStrategy(Types.VIP);
        BigDecimal money = strategy.getMoney(new BigDecimal("100"));
        System.out.println(money);
    }
}

//策略类型
enum Types {
    NORMAL("0", "普通客户"),
    VIP("1", "vip客户");
    private String type;
    private String desc;

    Types(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

//策略接口
interface Strategy {
    BigDecimal getMoney(BigDecimal money);
}

//普通策略
class NormalStrategy implements Strategy{

    @Override
    public BigDecimal getMoney(BigDecimal money) {
        Assert.notNull(money,"金额不能为空");
        return money;
    }
}
//VIP策略
class VIPStrategy implements Strategy{

    @Override
    public BigDecimal getMoney(BigDecimal money) {
        Assert.notNull(money,"金额不能为空");
        return money.multiply(new BigDecimal("0.8"));
    }
}
//策略工厂
class StrategyFactory {
    private static Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();
    static{
        strategyMap.put(Types.NORMAL.getType(), new NormalStrategy());
        strategyMap.put(Types.VIP.getType(), new VIPStrategy());
    }

    public static Strategy getStrategy(Types types) {
        Strategy strategy = strategyMap.getOrDefault(types.getType(), new NormalStrategy());
        return strategy;
    }
}