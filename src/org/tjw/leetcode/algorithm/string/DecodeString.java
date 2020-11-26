package org.tjw.leetcode.algorithm.string;

import org.tjw.luogu.tree.avl.AVLTree;

import static org.tjw.luogu.tree.avl.AVLTree.builder;

public class DecodeString {

    public static void main(String[] args) {
        Solution s = new DecodeString().new Solution();

        System.out.println(s.decodeString("3[a]2[bc]"));
        System.out.println(s.decodeString("3[a2[c]]"));
        System.out.println(s.decodeString("100[leetcode]"));
    }

    class Solution {
        public String decodeString(String s) {
            int len = s.length();
            StringBuilder strTemp = new StringBuilder();
            StringBuilder builder = new StringBuilder();
            for(int j = 0;j < len;j ++) {
                char c = s.charAt(j);
                if('0' <= c && c <= '9') {
                    j = recurtion(j, len, c, builder, strTemp, s);
                } else {
                    builder.append(c);
                }
            }
            return builder.toString();
        }

        public int recurtion(int j, int len, char c, StringBuilder builder, StringBuilder strTemp, String s) {
            int tens = 10;
            int times = 0;
            while(j < len && '0' <= c && c <= '9') {
                times = times * tens + c - '0';
                c = s.charAt(++ j);
            }
            if(times != 0) {
                c = s.charAt(++ j);
                while(j < len && c != ']') {
                    if('0' <= c && c <= '9') {
                        j = recurtion(j, len, c, strTemp, new StringBuilder(), s);
                        c = s.charAt(++ j);
                    } else {
                        strTemp.append(c);
                        c = s.charAt(++ j);
                    }
                }
                String temp = strTemp.toString();
                strTemp.setLength(0);
                for(int i = 0;i < times;i ++) {
                    builder.append(temp);
                }
            }
            return j;
        }
    }
}
