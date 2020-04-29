package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.codewar.Trainning.SortTheOdd;

/**
 * You are given a string s containing lowercase English letters,
 * and a matrix shift, where shift[i] = [direction, amount]:
 *
 * direction can be 0 (for left shift) or 1 (for right shift).
 * amount is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s
 * and append it to the end.
 * Similarly, a right shift by 1 means remove the last character
 * of s and add it to the beginning.
 * Return the final string after all operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 * Example 2:
 *
 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * Output: "efgabcd"
 * Explanation:
 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 */

public class PerformStringShifts {

    public static void main(String[] args) {
        Solution solution = new PerformStringShifts().new Solution();

        String test00 = "joiazl";
        int[][] shifts = new int[][]{{1,1},{1,6},{0,1},{1,3},{1,0},{0,3}};
        System.out.print(solution.stringShift(test00,shifts));
    }

    class Solution {
        public String stringShift(String s, int[][] shift) {
            int len = s.length();
            char[] newchs = new char[s.length()];

            int head = 0;

            for(int i = 0;i < shift.length;i ++) {
                int[] temp = shift[i];
                if(temp[0] == 0) {
                    head = (head + len - temp[1]) % len;
                } else {
                    head = (head + temp[1]) % len;
                }
            }

            int j = 0;
            int end = (head + len - 1) % len;
            for(int i = head;i != end;i = (i + 1) % len) {
                newchs[i] = s.charAt(j ++);
            }
            newchs[end] = s.charAt(len - 1);

            return new String(newchs);
        }
    }
}
