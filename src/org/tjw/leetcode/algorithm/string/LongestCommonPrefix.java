package org.tjw.leetcode.algorithm.string;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();

        String[] strArr = new String[]{"ff","ff","ffdd"};
        String str = solution.longestCommonPrefix(strArr);
        System.out.println(str);
    }

    class Solution {
        String[] arr;
        boolean noPreFlag = false;
        String empty = "";

        public String longestCommonPrefix(String[] strs) {
            arr = strs;
            return divideHelper(0, strs.length - 1);
        }

        public String divideHelper(int begin, int end) {
            if(noPreFlag) return empty;
            if(end > begin) {
                int mid = (begin + end) >> 1;
                String pre01 = divideHelper(begin, mid);
                String pre02 = divideHelper(mid + 1, end);
                if(noPreFlag) return empty;

                int len = pre01.length() < pre02.length() ? pre01.length() : pre02.length();
                int index = -1;
                for(int i = -1;++ i < len;) {
                    if(pre01.charAt(i) != pre02.charAt(i)) {
                        index = i;
                        break;
                    }
                }
                if(index == -1) {
                    noPreFlag = true;
                    return empty;
                }
                return pre01.substring(0, index);
            }
            return arr[begin];
        }
    }
}
