package com.java.learn.interview;

public class TryReturnBeforeFinally {
    String a;

    public TryReturnBeforeFinally(String a) {
        this.a = a;
    }

    public static void main(String[] args) {
        TryReturnBeforeFinally tryReturnBeforeFinally = new TryReturnBeforeFinally("");
        String s = fun1(tryReturnBeforeFinally);
        System.out.println(s);
        System.out.println(tryReturnBeforeFinally.a);
    }
    public static String fun1(TryReturnBeforeFinally s){
        try{
            s.a+="1";
            return fun2(s);
        }finally{
            s.a+="3";
        }

    }

    private static String fun2(TryReturnBeforeFinally s) {

        return s.a+="2";
    }
}
