package org.tjw.leetcode.algorithm;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Callable;

public class test {

    int[] aaa = new int[10000000];
    int[] bbb = new int[10000000];
    int[] ccc = new int[10000000];
    int[] ddd = new int[10000000];
    int[] eee = new int[10000000];

    public void count() {
        long begin = System.currentTimeMillis();
        int count = 0;
        for(int i = 0;i < aaa.length;i++) count ++;
        for(int i = 0;i < bbb.length;i++) count ++;
        for(int i = 0;i < ccc.length;i++) count ++;
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

    }

    public static void main(String[] args) throws Exception {
        test test = new test();
        test.count();
    }

}
