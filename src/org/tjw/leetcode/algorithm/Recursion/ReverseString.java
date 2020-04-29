package org.tjw.leetcode.algorithm.Recursion;

/**
 * Write a function that reverses a string. The input string is given as an array
 * of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class ReverseString {

    public static void main(String[] args) {
        Solution solution = new ReverseString().new Solution();
        char[] s = new char[]{'1','h','e','l','l','o', '2'};
        solution.reverseString(s);
        for(int i = 0;i < s.length;i ++) {
            System.out.print(s[i]);
        }
    }

    class Solution {

        private int index = 0;
        private int rindex = 0;
        private int length = 0;

        public void reverseString(char[] s) {
            if(s.length <= 0) return;
            length = s.length - 1;
            helper(s);
        }

        public void helper(char[] s) {
            if(index < length) {
                char c = s[index];
                index ++;
                helper(s);
                s[rindex ++] = c;
            } else {
                char c = s[index];
                s[rindex ++] = c;
            }
        }
    }
}
