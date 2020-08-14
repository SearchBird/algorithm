package org.tjw.leetcode.algorithm.changlle30;

public class ValidPalindrome {


    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();

        String str01 = "A man, a plan, a canal: Panama";
        String str02 = "0p";
        String str03 = " apG0i4maAs::sA0m4i0Gp0";
        System.out.println(solution.isPalindrome(str01));
        System.out.println(solution.isPalindrome(str02));
        System.out.println(solution.isPalindrome(str03));
    }


    class Solution {
        public boolean isPalindrome(String s) {
            char[] chs = s.toCharArray();
            int begin = 0;int end = s.length() - 1;
            A:while(begin < end) {
                char b = chs[begin];
                while('0' > b || ('9' < b && b < 'A') || ('Z' < b && 'a' > b) || 'z' < b) {
                    begin ++;
                    continue A;
                }

                char e = chs[end];
                while('0' > e || ('9' < e && e < 'A') || ('Z' < e && 'a' > e) || 'z' < e) {
                    end --;
                    continue A;
                }

                int bb = get(b);int ee = get(e);begin ++; end --;
                if(bb != ee) return false;
            }
            return true;
        }

        public int get(char b) {
            int res = b - 'A';
            if(res > 26) res = b - 'a';
            return res;
        }
    }
}
