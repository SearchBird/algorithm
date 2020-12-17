package org.tjw.leetcode.algorithm.string;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        Solution s = new PalindromePartitioning().new Solution();
        System.out.println(s.partition("aab"));
        System.out.println(s.partition("dddddddddddd").size());
    }

    class Solution {
        String[] arr = new String[1000];
        public List<List<String>> partition(String s) {
            return recurtion(s, new ArrayList<List<String>>(), arr, 0, s.length(), 0, new StringBuilder());
        }

        public List<List<String>> recurtion(String s, List<List<String>> res, String[] temp, int index, int len, int arrindex, StringBuilder builder) {
            if(index == len) {
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < arrindex; i ++) list.add(temp[i]);
                res.add(list);
                return res;
            }
            for(int i = index;i < len;i ++) {
                String t = builder.append(s.charAt(i)).toString();
                if(isPaline(t)) {
                    arr[arrindex ++] = t;
                    recurtion(s, res, temp, i + 1, len, arrindex, new StringBuilder());
                    arrindex --;
                }
            }
            return res;
        }

        public boolean isPaline(String str) {
            int len = str.length() - 1;
            int mid = str.length() >> 1;
            for(int i = 0;i <= mid;i ++)
                if(str.charAt(i) != str.charAt(len - i)) return false;
            return true;
        }
    }
}
