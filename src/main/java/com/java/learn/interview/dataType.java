package com.java.learn.interview;

import java.util.Arrays;
import java.util.StringJoiner;

public class dataType {
    public static void main(String[] args) {
        System.out.println("------------------基本数据类型-------------------");
        BasicDataType[] values = BasicDataType.values();
        for (BasicDataType value : values) {
            System.out.println(value);
        }
    }
}

/**
 * 基本数据类型
 */
enum BasicDataType {
    BOOLEAN(boolean.class.getName(),1,0,1,Boolean.class.getName()),
    BYTE(byte.class.getName(),Byte.SIZE,Byte.MIN_VALUE,Byte.MAX_VALUE,Byte.class.getName()),
    CHAR(char.class.getName(),Character.SIZE,(int)Character.MIN_VALUE,(int)Character.MAX_VALUE,Character.class.getName()),
    SHORT(short.class.getName(),Short.SIZE,Short.MIN_VALUE,Short.MAX_VALUE,Short.class.getName()),
    INT(int.class.getName(),Integer.SIZE,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.class.getName()),
    LONG(long.class.getName(),Long.SIZE,Long.MIN_VALUE,Long.MAX_VALUE,Long.class.getName()),
    FLOAT(float.class.getName(),Float.SIZE,Float.MIN_VALUE,Float.MAX_VALUE,Float.class.getName()),
    DOUBLE(double.class.getName(),Double.SIZE,Double.MIN_VALUE,Double.MAX_VALUE,Double.class.getName()),
    ;
    private String basicType;
    private Object binaryNum;
    private Object minValue;
    private Object maxValue;
    private Object wrapClass;
    BasicDataType(String basicType, Object binaryNum, Object minValue, Object maxValue, Object wrapClass) {
        this.basicType = basicType;
        this.binaryNum = binaryNum;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.wrapClass = wrapClass;
    }

    public String getBasicType() {
        return basicType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BasicDataType.class.getSimpleName() + "[", "]")
                .add("basicType='" + basicType + "'")
                .add("binaryNum=" + binaryNum)
                .add("minValue=" + minValue)
                .add("maxValue=" + maxValue)
                .add("wrapClass=" + wrapClass)
                .toString();
    }

    /**
     * 增强for循环的方式
     * @param basicType 基本数据类型名称
     * @return
     */
    BasicDataType getBasicDataTypeByForEach(String basicType) {
        BasicDataType[] basicDataTypes = BasicDataType.values();
        for (BasicDataType basicDataType : basicDataTypes) {
            if (basicDataType.getBasicType().equals(basicType)) {
                return basicDataType;
            }
        }
        return null;
    }

    /**
     * stream流的方式
     * @param basicType 基本数据类型名称
     * @return
     */
    BasicDataType getBasicDataTypeByStream(String basicType) {
        return Arrays.asList(BasicDataType.values()).stream().filter(basicDataType -> basicDataType.getBasicType().equals(basicType))
                .findFirst().orElse(null);
    }
}