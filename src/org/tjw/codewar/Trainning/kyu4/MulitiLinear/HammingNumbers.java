package org.tjw.codewar.Trainning.kyu4.MulitiLinear;

import java.util.TreeSet;

public class HammingNumbers {

    public static void main(String[] args) {
        System.out.println(hamming2(3));
    }

    public static long hamming2(int n) {
        long[] tempArr = new long[n];
        tempArr[0] = 1;
        long a = 2, b = 3, c = 5;
        int aIdx = 0, bIdx = 0, cIdx = 0;
        for(int i = 1; i < n; i ++) {
            tempArr[i] = Math.min(Math.min(a,b), c);
            if(tempArr[i] == a) a = (tempArr[++ aIdx] << 1);
            if(tempArr[i] == b) b = tempArr[++ bIdx] * 3;
            if(tempArr[i] == c) c = tempArr[++ cIdx] * 5;
        }
        return tempArr[n - 1];
    }

    public static long hamming(int n) {
        TreeSet<Long> cache = new TreeSet<>();
        cache.add(1L);
        while(-- n > 0) {
            long first = cache.pollFirst();
            cache.add(first << 1);
            cache.add(first * 3);
            cache.add(first * 5);
        }
        return cache.first();
    }
}
