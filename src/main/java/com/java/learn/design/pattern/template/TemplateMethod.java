package com.java.learn.design.pattern.template;

public class TemplateMethod {
    public static void main(String[] args) {
        AbstractClass abstractClass = new ActualClass();
        abstractClass.operation();
    }
}

abstract class AbstractClass {
    protected void operation() {
        System.out.println("内部操作步骤");
        templateMethod();
        System.out.println("后续操作");
    }

    abstract protected void templateMethod();
}

class ActualClass extends AbstractClass {
    @Override
    protected void templateMethod() {
        System.out.println("子类模板操作");
    }
}