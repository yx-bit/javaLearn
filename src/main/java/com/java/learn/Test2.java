package com.java.learn;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class Test2 extends Test1 {
    private  String name="test";
    private static int i=1;
    private static int i1=100;//私有静态常量无法实例化引用,只能通过类名.私有静态常量
    private static int x=10;

    public int getNext() {
        return i++;
    }
    public void execute1() {
        System.out.println("Test2");
    }

    public void execute1(int m) {
        System.out.println("Test2");
    }
    public int execute1(int m,int n) {
        System.out.println("Test2");
        return 1;
    }
    static{x+=5;}



    public static void main(String[] args) {
        System.out.println(x);//StaticStuff 5
        Test2 test2 = new Test2();
        Test2 test21 = new Test2();
        test2.getNext();
        test21.getNext();
        System.out.println(test21.getNext());//3



        String a = new String("myString");
        String b="myString";
        String c = "my" + "String";
        String d=c;
        System.out.println(a==b);//false
        System.out.println(a==c);//false
        System.out.println(b==c);//true
        System.out.println(b==d);//true

        System.out.print(Integer.MAX_VALUE * 2);
        System.out.print(Integer.MIN_VALUE * 2);//-20

        System.out.println();
        abc();//a=98,b=98,c=97

        mystery(1234);//43211234

        test134to123(new int[]{1,3,4});

        isFloatTrue();

        System.out.println(Math.round(-1.5));

        System.out.println(100+5+"is");

        System.out.println("Admin".toLowerCase()=="admin");

        whileSwitchA();
        System.out.println("com.jd.".replaceAll(".","/"));
        System.out.println(Math.random());

        Integer[] intArray = {1,5, 2, 9, 1, 5,8};

        Integer[] sortedIntArray = bubbleSortArray((o1, o2) -> o1-o2, intArray);
        System.out.println(Arrays.toString(sortedIntArray));


        Integer[] intQuickArray = {1,5, 2, 9, 1, 5,8};

        Integer[] sortedIntQuickArray = quickSortArray((o1, o2) -> o1-o2, intQuickArray);
        System.out.println(Arrays.toString(sortedIntQuickArray));

    }
    private static <T> T[] quickSortArray(Comparator<T> comparator, T[] array){
        quickSort(array,comparator,0,array.length-1);
        return array;
    }

    private static <T> void quickSort(T[] array, Comparator<T> comparator, int low, int high) {
        System.out.println(low+":"+high+":"+(low<high));
        if (low < high) {
            int partitionIndex=partition(array, comparator, low, high);
            quickSort(array,comparator,low,partitionIndex-1);
            quickSort(array,comparator,partitionIndex+1,high);
        }
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int low, int high) {
        T pivot=array[high];
        int i=low-1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp=array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
        T temp=array[i+1];
        array[i+1]=array[high];
        array[high]=temp;
        return i+1;
    }

    private static <T> T[] bubbleSortArray(Comparator<T> comparator, T[] array){
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }

        return array;
    }
    private static void whileSwitchA() {
        int a=0;
        while (a < 5) {
            switch(a){
                case 0: case 3:a=a+1;
                case 1: case 2:a=a+2;
                default:a=a+3;
            }
        }
        System.out.println(a);
    }

    static{x/=3;}
    private static void isFloatTrue() {
        float f=42.0f;
        float f1[] = new float[2];
        float f2[] = new float[2];
        float f3[]=f1;
        long x=42;
        f1[0]=42.0f;
        System.out.println(f1==f2);//false
        System.out.println(x==f1[0]);//true
        System.out.println(f1==f3);//true
        System.out.println(f==f1[0]);//false

        int i=128;
        int j=i;
        //short s=i; 错误
        short s=128;
        long l=i;

        System.out.println(3*0.1==0.3);

        jValue();//2

        test123az();//123***45***
    }

    private static void test123az() {
        String str="123az45d";
        str = str.replaceAll("[a-z]+", "***");
        System.out.println(str);
    }

    private static void jValue() {
        int i=1;
        int j=i++;
        if ((j > ++j) && (i++ == j)) {
            j+=i;
        }
        System.out.println(j);
    }

    private static void test134to123(int[] arr) {
        String str = "[";

        for (int x=0; x<arr.length ; x++){
            if(x!=arr.length-1)
                str = str + arr[x] + ",";
            else
                str = str + arr[x] + "]";
        }
        System.out.println(str);
    }

    public static void abc() {
        int a, b, c; a = 'a'; b = 'b'; c = (a + b > 180 ? a++ : b++);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
    public static void mystery (int x)
    {
        System.out.print(x % 10);

        if ((x / 10) != 0)
        {
            mystery(x / 10);
        }
        System.out.print(x % 10);
    }


}
