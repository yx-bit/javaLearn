package com.java.learn.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BeansBuilder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public BeansBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> BeansBuilder<T> of(Supplier<T> instantiator) {
        return new BeansBuilder<>(instantiator);
    }

    public <P1> BeansBuilder<T> with(ConsumerBuilder<P1, T> consumer,P1 p ) {
        Consumer<T> c = instance -> consumer.accept(instance, p);
        modifiers.add(c);
        return this;
    }
    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
    @FunctionalInterface
    interface ConsumerBuilder<P, T> {
        void accept(T t, P p);
    }
    public static void main(String[] args) {
        B b = new B();
        b.id = 1;
        b.name="2";
        A build = BeansBuilder.of(A::new)
                .with(A::setId, b.id).build();
        System.out.println(build);
    }
    @Data
    static class  A{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Data
    static class  B{
        private int id;
        private String name;
    }
}
