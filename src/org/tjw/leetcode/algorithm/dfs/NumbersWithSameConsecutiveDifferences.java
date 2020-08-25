package org.tjw.leetcode.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/submissions/
 * 967. Numbers With Same Consecutive Differences
 * Medium
 * Add to List
 *
 * Share
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Note:
 *
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        Solution s = new NumbersWithSameConsecutiveDifferences().new Solution();
        int[] res = s.numsSameConsecDiff(3, 0);
        for(int i : res) System.out.print(i + " ");
    }

    private static int[] tensArr = new int[10];
    private static List<Integer> res;
    static {
        for(int i = 0;i < 10;i ++) tensArr[i] = (int) Math.pow(10,i);
    }
    class Solution {
        public int[] numsSameConsecDiff(int N, int K) {
            if(N == 1) return new int[]{0,1,2,3,4,5,6,7,8,9};
            res = new ArrayList<Integer>();
            for(int i = 1;i < 10;i ++) { // 先循环
                recurtion(N - 1, K, i, i * tensArr[N - 1]); // 后递归
            }
            int[] resArr = new int[res.size()];int index = 0;
            for(int i : res) resArr[index ++] = i;
            return resArr;
        }

        public void recurtion(int N, int K, int val, int total) {
            if(N == 0) {
                res.add(total);
                return;
            }
            int v1 = val + K; // 只需要判断 + K - K 的路程是否合理就行了
            if(v1 >= 0 && v1 < 10) recurtion(N - 1, K, v1, total + v1 * tensArr[N - 1]);
            int v2 = val - K; // 这里需要多一个 v2 != val 因为如果 K = 0，就会多走一次
            if(v2 >= 0 && v2 < 10 && v2 != val) recurtion(N - 1, K, v2, total + v2 * tensArr[N - 1]);
        }
    }
}
