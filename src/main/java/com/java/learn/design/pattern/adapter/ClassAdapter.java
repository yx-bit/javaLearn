package com.java.learn.design.pattern.adapter;

//类适配器模式
//继承模式
public class ClassAdapter extends OriginalData implements TargetData
{

    @Override
    public int output5v() {
        //继承原始数据类 取得实例
        int originData = this.output220v();
        //do Something
        System.out.println(String.format("%s  转换电压   %s",originData,5));
        return 5;
    }

    public static void main(String[] args) {
        ClassAdapter classAdapter = new ClassAdapter();
        classAdapter.output5v();
        //会暴露原接口  不符合最少知道原则
//        classAdapter.output220v();
    }
}
