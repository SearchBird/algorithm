package org.tjw.leetcode.algorithm.hashTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can
 * be replaced to get t.
 *
 * All occurrences of a character must be replaced with
 * another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {


    public static void main(String[] args) {
        Solution solution = new IsomorphicStrings().new Solution();

        // 1问题 只考虑了s对t映射，没考虑t对s映射，如果t重复，s不重复就会判断不出
        boolean str = solution.isIsomorphic("aac", "bbb");
        System.out.println(str);
            System.out.print(('Z' - 'a') + 1);
    }

    public class Solution {
        public boolean isIsomorphic(String s, String t) {
            final int BIN = 31;
            int[] mapMerge = new int[260];

            int len = s.length();
            for(int i = len;i -- > 0;) {
                int ss = s.charAt(i);
                int ttinit = t.charAt(i) + 4;

                int init = ss - 32;
                int slot = init / BIN;
                int pos = 1 << init % BIN;


                if((mapMerge[slot] & pos) == 0) {
                    if(mapMerge[ttinit] != 0 && mapMerge[ttinit] != init) return false;
                    mapMerge[slot] |= pos;
                    // 1问题 将tt数组映射存放为init的数值
                    mapMerge[ttinit] = init;
                    // 1问题 判断修改为init数值判断
                } else if(mapMerge[ttinit] != init) {
                    return false;
                }
            }
            return true;
        }
    }
}
