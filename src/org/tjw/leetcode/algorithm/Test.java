package org.tjw.leetcode.algorithm;


import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Throwable {

    }

    class Solution {
        public boolean isAlienSorted(String[] words, String order) {
            Map<Character, Integer> cache = new HashMap<Character, Integer>();
            char[] chs = order.toCharArray();
            for(int i = 0; i < chs.length; i ++) cache.put(chs[i], i);

            int len = words.length;
            for(int i = 1; i < len; i ++) {
                char[] chs1 = words[i - 1].toCharArray();
                char[] chs2 = words[i].toCharArray();

                boolean flag = true;
                int minL = Math.min(chs1.length, chs2.length);
                for(int j = 0; j < minL; j ++) {
                    int a = cache.get(chs1[j]), b = cache.get(chs2[j]);
                    if(a > b) return false;
                    else if(a < b) flag = false;
                }
                if(flag && chs1.length > chs2.length) return false;
            }
            return true;
        }
    }
}
