package com.java.learn.interview;

import java.util.ArrayList;
import java.util.List;

public class Filed {
    public static void main(String[] args) {
        int x=4;
        System.out.println((x++));
        System.out.println(x);
    }
}

class TestIt
{
    public static void main ( String[] args )
    {
        int[] myArray = {1, 2, 3, 4, 5};
        ChangeIt.doIt( myArray );
        for(int j=0; j<myArray.length; j++)
            System.out.println( myArray[j] + " " );

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        ChangeList.doIt(list);
        System.out.println(list.size());
    }
}
class ChangeIt
{
    static void doIt( int[] z )
    {
        z = null ;
    }
}
    class ChangeList
{
    static void doIt( List z )
    {
        z.add(3);
    }
}