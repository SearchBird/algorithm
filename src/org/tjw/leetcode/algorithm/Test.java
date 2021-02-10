package org.tjw.leetcode.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    private static int times = 0;
    public static void main(String[] args) throws Throwable {
        Solution s = new Test().new Solution();
        int[] res = s.shortestToChar("loveleetcode",'e');
        for(int i : res) System.out.print(i + " ");
        System.out.println();
    }

    class Solution {
        public int[] shortestToChar(String s, char c) {
            char[] chs = s.toCharArray();
            int[] dis = new int[chs.length];
            recurtion(chs, dis, new boolean[chs.length], 0, chs.length, c);
            return dis;
        }

        public void recurtion(char[] chs, int[] dis, boolean[] visited, int index, int len, char target) {
            for(int i = index; i < len; i ++) {
                if(visited[i]) continue;
                if(chs[i] == target) {
                    visited[i] = true;
                    int left = i - 1, right = i + 1;
                    while(left >= 0) {
                        if(chs[left] == target) {
                            if(visited[left]) break;
                            recurtion(chs, dis, visited, left, len, target); break;
                        }
                        if(visited[left]) {
                            if(dis[left] > i - left) dis[left] = i - left;
                            else break;
                            left --;
                            continue;
                        }
                        visited[left] = true;
                        dis[left] = i - left;
                        left --;
                    }
                    while(right < len) {
                        if(chs[right] == target) {
                            if(visited[right]) break;
                            recurtion(chs, dis, visited, right, len, target); break;
                        }
                        if(visited[right]) {
                            if(dis[right] > right - i) dis[right] = right - i;
                            else break;
                            right ++;
                            continue;
                        }
                        visited[right] = true;
                        dis[right] = right - i;
                        right ++;
                    }
                }
            }
        }
    }
}
