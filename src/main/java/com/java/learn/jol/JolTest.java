package com.java.learn.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.List;

public class JolTest {
    public static void main(String[] args) {
        oom();

    }

    static class User {

    }
    //测试内存溢出
    //-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/jvm_sever1.hprof 或不要文件名或相对路径或默认项目目录
    //实时生成
    // # 使用方法：
    //# jcmd
    //jcmd <pid> GC.heap_dump <file_path>
    //# 示例：
    //jcmd 3873 GC.heap_dump /data/tmp/heapdump.hprof
    //# jmap
    //jmap -dump:format=b,file=<file-path\> <pid>
    //# 示例：
    //jmap -dump:live,format=b,file=/data/tmp/heapdump.hprof 3873
    static void oom() {
        List<User> list = new ArrayList<>();
        while (true) {
            User user = new User();
            list.add(user);
        }
    }
    //jol
    static void jvmDetails() {
        System.out.println(VM.current().details());
        //# Running 64-bit HotSpot VM.
        //# Using compressed oop with 3-bit shift.
        //# Using compressed klass with 3-bit shift.
        //# WARNING | Compressed references base/shifts are guessed by the experiment!
        //# WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
        //# WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
        //# Objects are 8 bytes aligned.  // 以 8 bytes的粒度对齐
        //# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]    // 分别对应[Oop(Object Original Pointer), boolean, byte, char, short, int, float, long, double]的大小
        //# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]    // 数组中元素的大小，分别对应的是[Oop(Object Original Pointer), boolean, byte, char, short, int, float, long, double]
    }
}
