package org.tjw.leetcode.algorithm.Recursion.RecursionToIteration;

import java.util.*;

/**
 * Given n pairs of parentheses, write a function to generate
 * all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {


    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(30));
    }

    class Solution {

        char lc = '(';
        char rc = ')';
        ArrayList<String> link = new ArrayList<>();
        int index = 0;
        char[] chars;

        public List<String> generateParenthesis(int n) {
            if(n == 0) return link;
            chars = new char[2 * n];
            chars[index] = lc;
            generate(n - 1, n);
            return link;
        }

        public void generate(int left, int right) {
            if(left > 0) {
                chars[++ index] = lc;
                generate(left - 1, right);
            }
            if(right > left && right > 0) {
                chars[++ index] = rc;
                generate(left, right - 1);
            }

            if(left == 0 && right == 0)
                link.add(new String(chars));
            index --;
        }
    }
}
