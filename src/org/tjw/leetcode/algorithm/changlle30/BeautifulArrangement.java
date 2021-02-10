package org.tjw.leetcode.algorithm.changlle30;

import java.io.BufferedReader;
import java.io.FileReader;

public class BeautifulArrangement {

    public static void main(String[] args) throws Throwable {
        Solution s = new BeautifulArrangement().new Solution();
        System.out.println(s.countArrangement(1));
        System.out.println(s.countArrangement(2));
        System.out.println(s.countArrangement(3));
        System.out.println(s.countArrangement(4));
        System.out.println(s.countArrangement(5));
        System.out.println(s.countArrangement(6));
        System.out.println(s.countArrangement(7));
        System.out.println(s.countArrangement(8));
        System.out.println(s.countArrangement(9));
        System.out.println(s.countArrangement(10));
        System.out.println(s.countArrangement(11));
        System.out.println(s.countArrangement(12));
        System.out.println(s.countArrangement(13));
        System.out.println(s.countArrangement(14));
        System.out.println(s.countArrangement(15));

    }

    private int[] map = new int[]{1, 2, 3, 8, 10, 36, 41, 132, 250,
            700, 750, 4010, 4237, 10680, 24679};

    class Solution {
        int count = 0;
        public int countArrangement(int n) {
            count = 0;
            recurtion(n + 1, new boolean[n + 1], 1, 0);
            return count;
        }

        public void recurtion(int n, boolean[] visited, int index, int cur) {
            if(index == n) {
                count ++;
                return;
            }
            for(int i = 1;i < n;i ++) {
                if(i == cur) continue;
                if((i % index == 0 || index % i == 0) && !visited[i]) {
                    visited[i] = true;
                    recurtion(n, visited, index + 1, i);
                    visited[i] = false;
                }
            }
        }
    }
}
