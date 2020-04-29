package org.tjw.leetcode.algorithm.hashTable;

import java.util.HashMap;

/**
 * Given a string, find the first non-repeating character in it
 * and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterinaString {
    public static void main(String[] args) {
        Solution solution = new FirstUniqueCharacterinaString().new Solution();

        // 问题1 注意空字符
        System.out.print(solution.firstUniqChar(""));
    }

    class Solution {
        public int firstUniqChar(String s) {
            int[] chars = new int[26];
            int[] index = new int[26];
            int len = s.length();
            for(int i = 0;i < len;i ++) {
                char ch = s.charAt(i);
                int pos = ch - 'a';
                chars[pos] += 1;
                if(index[pos] == 0) {
                    index[pos] = i + 1;
                }
            }

            int min = Integer.MAX_VALUE;
            for(int i = 0;i < 26;i ++) {
                if(chars[i] == 1 && min > index[i] - 1) {
                    min = index[i] - 1;
                }
            }

            // 问题1 这里注意判断如果没有重复或者为空，需要返回-1
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }
}
