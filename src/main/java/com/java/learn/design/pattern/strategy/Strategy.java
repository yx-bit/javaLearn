package com.java.learn.design.pattern.strategy;

//多态的应用
public class Strategy {
    public static void main(String[] args) {
        Zombie generalZombie = new GeneralZombie();
        generalZombie.display();
        generalZombie.move();
        generalZombie.attach();
        generalZombie.setAttachAble(new HiteAttack());
        generalZombie.attach();
    }
}
//抽象的动作  稳定不变的
interface Moveable {
    void move();
}
//抽象的动作  稳定不变的
interface AttachAble {
    void attach();
}
//定义一个抽象的类去持有稳定不变的原型
abstract class Zombie {
    //共有的特质
    abstract public void display();
    //抽象的动作
    Moveable moveable;
    AttachAble attachAble;
     void move(){
         moveable.move();
     };

    void attach(){
        attachAble.attach();
    }

    public Zombie(Moveable moveable, AttachAble attachAble) {
        this.moveable = moveable;
        this.attachAble = attachAble;
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public AttachAble getAttachAble() {
        return attachAble;
    }

    public void setAttachAble(AttachAble attachAble) {
        this.attachAble = attachAble;
    }
}

//具体实现
class GeneralZombie extends Zombie{
    public GeneralZombie() {
        super(new StepByStepMove(),new BiteAttack());
    }

    public GeneralZombie(Moveable moveable, AttachAble attachAble) {
        super(moveable,attachAble);
    }

    @Override
    public void display() {
        System.out.println("我是普通僵尸");
    }
}

class StepByStepMove implements Moveable {
    @Override
    public void move() {
        System.out.println("一步一步移动");
    }
}

class BiteAttack implements AttachAble {
    @Override
    public void attach() {
        System.out.println("咬");
    }
}
class HiteAttack implements AttachAble {
    @Override
    public void attach() {
        System.out.println("打");
    }
}