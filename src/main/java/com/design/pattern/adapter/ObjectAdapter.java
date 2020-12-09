package com.design.pattern.adapter;

//对象适配器模式
public class ObjectAdapter implements TargetData{
    //组合模式
    OriginalData originalData;

    public ObjectAdapter(OriginalData originalData) {
        this.originalData = originalData;
    }

    @Override
    public int output5v() {
        int originData = originalData.output220v();
        //do Something
        System.out.println(String.format("%s  转换电压   %s",originData,5));
        return 5;
    }

    public static void main(String[] args) {
        ObjectAdapter objectAdapter = new ObjectAdapter(new OriginalData());
        objectAdapter.output5v();
    }
}

interface TargetData{
    int output5v();
}

class OriginalData {
    int output220v() {
        return 220;
    }
}