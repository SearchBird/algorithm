package org.tjw.leetcode.algorithm;

import org.tjw.leetcode.algorithm.linkedList.ListNode;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        String str = "11";
        String str2 = new String("11");
        String str3 = new String("11");
        System.out.println(str == str2);
        System.out.println(str3 == str2);
    }

    static class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            for(int i = 0, len = flowerbed.length; i < len; i ++)
                if(flowerbed[i] == 0) {
                    boolean left = true;
                    if(i - 1 >= 0) left = flowerbed[i - 1] != 1;
                    boolean right = true;
                    if(i + 1 < len) right = flowerbed[i + 1] != 1;
                    if(left && right) {
                        if(n <= 0) return true;
                        flowerbed[i] = 1;
                        n --;
                    }

                }
            return n == 0;
        }
    }

}

/**
 * P Y A I H
 * A P L S
 */
