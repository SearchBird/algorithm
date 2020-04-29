package org.tjw.leetcode.algorithm.set;

import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args) {
        Solution solution = new HappyNumber().new Solution();
        System.out.print(solution.isHappy(7));

    }
    class Solution {
        public boolean isHappy(int n) {
            HashSet<Integer> set = new HashSet<Integer>();
            int sum = 0;
            while(n != 1) {
                while(n / 10 != 0 || n % 10 != 0) {
                    int num = n % 10;
                    sum += num * num;
                    n /= 10;
                }
                if(set.contains(sum)) return false;
                set.add(sum);
                n = sum;
                sum = 0;
            }
            return true;
        }
    }
}
