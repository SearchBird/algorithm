package org.tjw.leetcode.algorithm.string;

import java.util.HashMap;
import java.util.Map;

public class DecodedStringatIndex {

    public static void main(String[] args) {
        Solution s = new DecodedStringatIndex().new Solution();
        System.out.println(s.decodeAtIndex("leet2code3", 10));
        System.out.println(s.decodeAtIndex("ha22", 5));
        System.out.println(s.decodeAtIndex("a2345678999999999999999", 10));
        System.out.println(s.decodeAtIndex("a2b3c4d5e6f7g8h9", 623529));
    }

    class Solution {
        public String decodeAtIndex(String S, int K) {
            long pre = 0;
            int temp, len = S.length(), i = 0;
            char[] chs = S.toCharArray();
            for(;i < len;i ++) {
                if(pre >= K) {
                    while(i -- > 0) {
                        if(0 < (temp = chs[i] - '0') && temp <= 9) {
                            pre /= chs[i] - '0';
                            K %= pre;
                        } else {
                            if(K % pre == 0) return String.valueOf(chs[i]);
                            pre --;
                        }
                    }
                }
                if(0 < (temp = chs[i] - '0') && temp <= 9) pre *= temp;
                else pre ++;
            }
            if(pre >= K) {
                while(i -- > 0) {
                    if(0 < (temp = chs[i] - '0') && temp <= 9) {
                        pre /= chs[i] - '0';
                        K %= pre;
                    } else {
                        if(K % pre == 0) return String.valueOf(chs[i]);
                        pre --;
                    }
                }
            }
            return "";
        }
    }

    class Solution2 {
        public String decodeAtIndex(String S, int K) {
            int temp, i = 0;
            long count = 0;
            for(; count < K; i ++) // 1、统计长度
                count = 0 < (temp = S.charAt(i) - '0') && temp <= 9 ? count * temp : count + 1;
            while(i -- > 0) { // 然后再解码
                if(0 < (temp = S.charAt(i) - '0') && temp <= 9) {
                    count /= temp;
                    K %= count;
                } else {
                    if(K % count == 0) return String.valueOf(S.charAt(i));
                    count --;
                }
            }
            return "";
        }
    }
}
