package org.tjw.leetcode.algorithm.Recursion.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Given two integers n and k, return all possible combinations
 * of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combinations {
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        System.out.println(solution.combine(4,2));
    }


    class Solution {
        List<List<Integer>> list = new Vector<List<Integer>>();
        int top = 0;

        public List<List<Integer>> combine(int n, int k) {
            for(int i = 1;i <= n - k + 1;i ++) {
                List<Integer> intList = new Vector<Integer>();
                intList.add(i);
                combine(intList, n, k - 1, i);
            }
            return list;
        }

        public void combine(List<Integer> intList, int n, int k, int begin) {
            if(k > 0) {
                int index = begin + 1;
                int len = n - k + 1;
                while(index <= len) {
                    intList.add(++ top, index);
                    combine(intList, n, k - 1, index);
                    intList.remove(top --);
                    index ++;
                }
            }

            if(k == 0) {
                Vector<Integer> addList = new Vector<Integer>();
                addList.addAll(intList);
                list.add(addList);
            }
        }
    }
}
