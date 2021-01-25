package com.java.learn.util;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * BUilder构造模式
 *
 * @param <T>
 */
public class Builder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    public <P1> Builder<T> with(P1 p1, ConsumerOne<P1, T> consumer) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(P1 p1, P2 p2, ConsumerTwo<P1, P2, T> consumer) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
}

@FunctionalInterface
interface ConsumerOne<P1, T> {
    void accept(T t, P1 p1);
}

@FunctionalInterface
interface ConsumerTwo<P1, P2, T> {
    void accept(T t, P1 p1, P2 p2);
}

class Firend {
    public static void main(String[] args) {
        Firend build = Builder
                //构建对象
                .of(Firend::new)
                //多参数
                .with("0", "情人节礼物", Firend::setGift)
                //单参数
                .with(20, Firend::setAge)
                .with("小美", Firend::setName)
                .build();

        System.out.println(build.getAge() + build.getName());
    }

    private String name;
    private int age;
    private String day;
    private String gift;

    public void setGift(String day, String gift) {
        this.day = day;
        this.gift = gift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}