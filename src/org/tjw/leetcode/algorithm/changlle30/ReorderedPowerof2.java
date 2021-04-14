package org.tjw.leetcode.algorithm.changlle30;

import java.util.HashSet;
import java.util.Set;

public class ReorderedPowerof2 {

    public static void main(String[] args) {
        Solution s = new ReorderedPowerof2().new Solution();
        System.out.print(s.reorderedPowerOf2(1240));
        System.out.print(s.reorderedPowerOf2(61));
    }

    private static Set<Integer> cache = new HashSet<Integer>();

    static {
        for(int i = 0; i < 32; i ++) {
            cache.add(1 << i);
        }
    }
    class Solution {
        boolean flag = false;

        public boolean reorderedPowerOf2(int N) {
            char[] chs = String.valueOf(N).toCharArray();
            boolean[] visited = new boolean[chs.length];
            for(int i = 0; i < chs.length; i ++) {
                if(chs[i] - '0' != 0) {
                    visited[i] = true;
                    recurtion(chs, visited, chs[i] - '0', 1, chs.length);
                    visited[i] = false;
                }
            }
            return flag;
        }

        public void recurtion(char[] chs, boolean[] visited, int total, int index, int len) {
            if(flag) return;
            if(index == len) {
                flag = cache.contains(total);
                return;
            }
            total *= 10;
            index ++;
            for(int i = 0; i < len; i ++) {
                if(!visited[i]) {
                    visited[i] = true;
                    recurtion(chs, visited, total + chs[i] - '0', index, len);
                    visited[i] = false;
                }
            }
        }
    }
}
