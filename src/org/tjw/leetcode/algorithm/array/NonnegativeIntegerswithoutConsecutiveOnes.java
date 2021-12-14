package org.tjw.leetcode.algorithm.array;

public class NonnegativeIntegerswithoutConsecutiveOnes {

    public static void main(String[] args) {
        Solution s = new NonnegativeIntegerswithoutConsecutiveOnes().new Solution();
        System.out.println(s.findIntegers(3));
        System.out.println(s.findIntegers(4));
        System.out.println(s.findIntegers(5));
        System.out.println(s.findIntegers(11));
        System.out.println(s.findIntegers(17));
    }

    class Solution {
        public int findIntegers(int n) {
            int[] pre = new int[32], total = new int[32];
            pre[0] = total[0] = 1;
            pre[1] = 1;
            total[1] = 2;
            for(int i = 2; i < 32; i ++) {
                pre[i] = total[i - 2];
                total[i] = pre[i] + total[i - 1];
            }

            boolean duplicate = false;
            int res = 0;
            for(int i = 31, tmp = 1 << i; i >= 0; tmp = 1 << -- i)
                if((n & tmp) != 0) {
                    res += total[i];
                    if(duplicate) return res;
                    duplicate = true;
                }
                else duplicate = false;
            return res + 1;
        }
    }

}
