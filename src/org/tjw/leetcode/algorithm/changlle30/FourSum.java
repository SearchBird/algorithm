package org.tjw.leetcode.algorithm.changlle30;

import java.util.Arrays;
import java.util.HashMap;

public class FourSum {

    public static void main(String[] args) {
        Solution s = new FourSum().new Solution();
        System.out.println(s.fourSumCount(
                new int[]{-1, -1},
                new int[]{-1, 1},
                new int[]{-1, 1},
                new int[]{1, -1}
        ));
        System.out.println(s.fourSumCount(
                new int[]{1,2},
                new int[]{-2,-1},
                new int[]{-1,2},
                new int[]{0,2}
        ));
    }

    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            int len = A.length, count = 0, times = len * len;
            int AB[] = new int[times];
            int CD[] = new int[times];
            for(int i = 0;i < len;i ++) {
                int slot = i * len;
                for(int j = 0;j < len;j ++) {
                    AB[slot + j] = A[i] + B[j];
                    CD[slot + j] = - C[i] - D[j];
                }
            }
            Arrays.sort(AB); // 排序一边可以快点
            Arrays.sort(CD);
            int i = 0,j = 0;
            A:for(;i < times;) { // ++ i 放到了循环内处理
                for(;j < times;j ++) {
                    if(AB[i] == CD[j]) {
                        int ABT = 1, CDT = 1;
                        // 注意要先 ++ i
                        while(++ i < times && AB[i] == AB[i - 1]) ABT ++; // 把一样的都进行累加
                        while(++ j < times && CD[j] == CD[j - 1]) CDT ++;
                        count += ABT * CDT;
                        continue A;
                    } else if(AB[i] < CD[j]) break;
                } i ++;
            }
            return count;
        }
    }

    class Solution2 {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            int len = A.length, count = 0;
            HashMap<Integer,Integer> mapA = new HashMap<Integer,Integer>();
            HashMap<Integer,Integer> mapB = new HashMap<Integer,Integer>();
            for(int i = 0;i < len;i ++) {
                for(int j = 0;j < len;j ++) {
                    int a = A[i] + B[j];
                    int b = - C[i] - D[j];
                    mapA.put(a, mapA.getOrDefault(a, 0) + 1);
                    mapB.put(b, mapB.getOrDefault(b, 0) + 1);
                }
            }
            for(int value : mapA.keySet())
                if(mapB.containsKey(value)) count += mapA.get(value) * mapB.get(value);
            return count;
        }
    }
}
