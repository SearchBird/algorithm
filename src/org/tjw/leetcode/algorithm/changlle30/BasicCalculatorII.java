package org.tjw.leetcode.algorithm.changlle30;

public class BasicCalculatorII {

    public static void main(String[] args) {
        Solution s = new BasicCalculatorII().new Solution();
        System.out.println(s.calculate("3+2*2"));
        System.out.println(s.calculate("3+2*2/2 - 1+1"));
        System.out.println(s.calculate("3+1 22*2/1 2 2 - 1+1"));
    }

    private static int tens = 10;
    class Solution {
        private int index = 0;
        public int calculate(String s) {
            index = 0;
            char[] chs = s.toCharArray();
            int len = s.length(), total = match(len, chs);

            while(index < len) {
                char c = chs[index ++];
                switch(c) {
                    case '+' : total += match(len, chs); break;
                    case '-' : total -= match(len, chs); break;
                }
            }
            return total;
        }

        public int match(int len, char[] chs) {
            int num = 0;
            A:for(; index < len;) {
                char c = chs[index];
                switch(c) {
                    case ' ' : index ++; continue;
                    case '+' : case '-' : break A;
                    case '*' : index ++; num *= match2(len, chs); break;
                    case '/' : index ++; num /= match2(len, chs); break;
                    default : num = num * tens + (c - '0'); index ++;
                }
            }
            return num;
        }

        public int match2(int len, char[] chs) {
            int num = 0;
            A:for(; index < len; index ++) {
                char c = chs[index];
                switch(c) {
                    case ' ' : continue;
                    case '+' : case '-' : case '*' : case '/' : break A;
                    default : num = num * tens + (c - '0');
                }
            }
            return num;
        }
    }
}
