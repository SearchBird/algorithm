package org.tjw.leetcode.algorithm;

public class test2 {
    int[] aaa;
    int[] bbb;
    int[] ccc;
    int[] ddd;
    int[] eee;

    public test2() {
        aaa = new int[10000000];
        bbb = new int[10000000];
        ccc = new int[10000000];
        ddd = new int[10000000];
        eee = new int[10000000];
    }

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
        test2 test = new test2();
        test.count();
    }

}
