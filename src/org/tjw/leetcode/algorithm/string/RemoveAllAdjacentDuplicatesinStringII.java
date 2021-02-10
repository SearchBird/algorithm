package org.tjw.leetcode.algorithm.string;

public class RemoveAllAdjacentDuplicatesinStringII {

    public static void main(String[] args) {
        Solution s = new RemoveAllAdjacentDuplicatesinStringII().new Solution();
        System.out.println(s.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(s.removeDuplicates("aabbbccc", 3));
    }

    class Solution {
        public String removeDuplicates(String s, int k) {
            int pre = 0, next = 1, back, len = s.length(), count = 0, kd = k - 2;
            char[] chs = s.toCharArray();
            StringBuilder builder = new StringBuilder();
            while(next < len) {
                if(chs[pre] == chs[next]) {
                    if(count == kd) {
                        chs[next] = 0;
                        int pred = k - 1;
                        while(pred -- > 0) { // 前指针回撤都得排除 0
                            while(chs[pre] == 0) pre --;
                            chs[pre] = 0;
                        }
                        next ++;
                        while(pre > 0 && chs[pre] == 0) pre --;
                        if(pre < 0 || chs[pre] == 0) {
                            next ++;
                            pre = next - 1;
                        }
                        back = pre - 1;
                        count = 0;
                        while(back >= 0) { // 回计指针回撤都得排除 0
                            if(chs[back] == 0) back --;
                            else
                                if(chs[back] == chs[pre]) {
                                    count ++;
                                    while(-- back >= 0 && chs[back] == 0);
                                }
                                else break;
                        }
                    } else { // 前指针回撤都得排除 0
                        next ++;
                        while(pre < next && chs[++ pre] == 0);
                        count ++;
                    }
                } else { // 前指针回撤都得排除 0
                    next ++;
                    while(pre < next && chs[++ pre] == 0);
                    count = 0;
                }
            }
            for(char ch : chs) if(ch != 0) builder.append(ch);
            return builder.toString();
        }
    }
}
