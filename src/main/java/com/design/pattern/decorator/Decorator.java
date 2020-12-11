package com.design.pattern.decorator;

public class Decorator  {
    public static void main(String[] args) {
        Component component = new ActualComponent1(new ActualComponent(new OriginalComponent()));
        component.operation();
    }
}

interface Component {
    void operation();
}
 abstract class DecoratorComponent  implements Component{
    Component component;

    public DecoratorComponent(Component component) {
        this.component = component;
    }
}
class OriginalComponent implements Component {

    @Override
    public void operation() {
        System.out.println("原始操作");
    }
}

class ActualComponent extends DecoratorComponent {

    public ActualComponent(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("新增操作");
    }
}
class ActualComponent1 extends DecoratorComponent {

    public ActualComponent1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("新增操作1");
    }
}