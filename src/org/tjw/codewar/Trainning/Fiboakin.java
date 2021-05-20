package org.tjw.codewar.Trainning;

import java.util.*;

public class Fiboakin {

    public static void main(String[] args) {
        Fiboakin fiboakin = new Fiboakin();
        System.out.println(fiboakin.lengthSupUK(50, 25));
        System.out.println(fiboakin.lengthSupUK(3332, 973));
        System.out.println(fiboakin.lengthSupUK(2941, 862));
        System.out.println(fiboakin.lengthSupUK(3177, 573));
        System.out.println(fiboakin.lengthSupUK(1745, 645));
        System.out.println(fiboakin.comp(74626));
        System.out.println(fiboakin.comp(71749));
        System.out.println(fiboakin.comp(56890));
    }

    private static Map<Integer, Integer> cache = new HashMap<>();

    static {
        cache.clear();
        cache.put(1, 1);
        cache.put(2, 1);
        cache.put(3, 2);
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
    }

    public static long lengthSupUK(int n, int k) {
        int i = 0;
        while(i < n) {
            if(i + 5000 < n) i += 5000;
            else i = n;
            recurtion(i);
        }

        long res = 0;
        for(int j = n; j > 0; j --)
            if(cache.get(j) >= k) res ++;
        return res;
    }

    private static int recurtion(int n) {
        if(cache.containsKey(n)) return cache.get(n);
        int res = recurtion(n - recurtion(n - 1)) + recurtion(n - recurtion(n - 2));
        cache.put(n, res);
        return res;
    }

    public static long comp(int n) {
        int i = 0;
        while(i < n) {
            if(i + 5000 < n) i += 5000;
            else i = n;
            recurtion(i);
        }

        long res = 0;
        for(int j = n; j > 15; j --)
            if(cache.get(j) < cache.get(j - 1)) res ++;
        return res;
    }
}
