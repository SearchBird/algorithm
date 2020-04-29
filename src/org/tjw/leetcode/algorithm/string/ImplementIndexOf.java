package org.tjw.leetcode.algorithm.string;

public class ImplementIndexOf {
    public static void main(String[] args) {
        Solution solution = new ImplementIndexOf().new Solution();

        int index = solution.strStr("mississippi", "issip");
        System.out.println(index);
    }
    class Solution {
        /**
         * 头查法
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr(String haystack, String needle) {
            if(needle == null || needle.isEmpty()) return 0;
            if(haystack == null) return -1;

            int needIndex = 0;
            int hayIndex = -1;
            int mark = -1;
            for(;++ hayIndex < haystack.length();) {
                if(haystack.charAt(hayIndex) == needle.charAt(needIndex)) {
                    // 需要设置mark, 匹配不正确要回到mark + 1, 不然会错过检测
                    if(mark == -1) mark = hayIndex;
                    if(++ needIndex == needle.length()) {
                        return hayIndex - needIndex + 1;
                    }
                } else {
                    // 重新修改之后还得再匹配, 不然就跳过当前的, 无法检测
                    needIndex = 0;
                    // 必须跟着mark标记判断, 不然会干扰没有mark的事件
                    if(mark != -1) {
                        hayIndex = mark + 1;
                        mark = -1;
                    }
                    if(haystack.charAt(hayIndex) == needle.charAt(needIndex)) needIndex ++;
                }
            }
            return -1;
        }


        /**
         * 尾查法--未处理完善
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr2(String haystack, String needle) {
            if(needle == null || needle.equals("")) return 0;
            if(haystack == null) return -1;
            char[] hay = haystack.toCharArray();
            char[] need = needle.toCharArray();

            int origin = need.length - 1;
            int needIndex = origin;
            int hayIndex = hay.length;
            for(;hayIndex -- > 0;) {
                if(hay[hayIndex] == need[needIndex]) {
                    if(-- needIndex < 0) {
                        break;
                    }
                } else {
                    needIndex = origin;
                }
            }
            return hayIndex;
        }
    }
}
