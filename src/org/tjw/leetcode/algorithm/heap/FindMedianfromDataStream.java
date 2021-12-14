package org.tjw.leetcode.algorithm.heap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class FindMedianfromDataStream {

    public static void main(String[] args) {
        MedianFinder finder = new FindMedianfromDataStream().new MedianFinder();
        finder.addNum(1);
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        finder.findMedian();
    }

    class MedianFinder {
        int smallSize = 0, bigSize = 0;
        TreeMap<Integer,Integer> small = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        TreeMap<Integer,Integer> big = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        /** initialize your data structure here. */
        public MedianFinder() {
        }

        public void addNum(int num) {
            if(small.isEmpty()) {
                small.put(num, 1); smallSize ++; return;
            }
            if(num > small.firstKey()) {
                big.put(num, big.getOrDefault(num, 0) + 1);
                bigSize ++;
            } else {
                small.put(num, small.getOrDefault(num, 0) + 1);
                smallSize ++;
            }
            if(bigSize > smallSize) {
                swapMap(big, small);
                bigSize --; smallSize ++;
            } else if(smallSize > bigSize + 1) {
                swapMap(small, big);
                bigSize ++; smallSize --;
            }
        }

        public double findMedian() {
            return smallSize > bigSize ? small.firstKey() : (double)(small.firstKey() + big.firstKey()) / 2;
        }

        public void swapMap(TreeMap<Integer, Integer> o1, TreeMap<Integer, Integer> o2) {
            Map.Entry<Integer, Integer> e = o1.firstEntry();
            int k = e.getKey(), v = e.getValue();
            if(v != 1) {
                o1.put(k, v - 1);
            } else {
                e = o1.pollFirstEntry();
            }
            o2.put(k, o2.getOrDefault(k, 0) + 1);
        }
    }
}
