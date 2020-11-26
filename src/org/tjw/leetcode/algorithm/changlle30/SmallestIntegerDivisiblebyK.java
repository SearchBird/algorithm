package org.tjw.leetcode.algorithm.changlle30;

public class SmallestIntegerDivisiblebyK {

    public static void main(String[] args) {
        Solution s = new SmallestIntegerDivisiblebyK().new Solution();

        System.out.println(s.smallestRepunitDivByK(23));
        System.out.println(s.smallestRepunitDivByK(17));
        System.out.println(s.smallestRepunitDivByK(2));
        System.out.println(s.smallestRepunitDivByK(5));
        int[] arr = new int[]{
                1,11,19,7,2,21,4,18,20,17,10,9,22,14,3,8,12,6};
        int total = 0;
        for(int i : arr) {
            total ^= i;
        }
        System.out.println(total);
    }

    class Solution {
        public int smallestRepunitDivByK(int K) {
            if((K & 1) != 0) {
                boolean[] map = new boolean[50000];
                for(int mod = 1 % K, times = 0;!map[mod];++ times, map[mod] = true, mod = (mod * 10 + 1) % K)
                    if(mod == 0) return times;
            }
            return -1;
        }
    }
}
