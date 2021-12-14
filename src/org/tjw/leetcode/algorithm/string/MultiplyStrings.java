package org.tjw.leetcode.algorithm.string;

public class MultiplyStrings {

    public static void main(String[] args) {
        Solution s = new MultiplyStrings().new Solution();
        s.multiply("123", "123");
        s.multiply("99", "99");
        s.multiply("0", "0");
    }

    class Solution {
        public String multiply(String num1, String num2) {
            char[] chs1 = num1.toCharArray(), chs2 = num2.toCharArray();
            int[] total3 = new int[chs1.length + chs2.length + 1];
            int len1 = chs1.length, len2 = chs2.length, carry = 0;

            for(int i = len2; i -- > 0;) {
                for(int j = len1; j -- > 0;) {
                    int tmp = (chs1[j] - '0') * (chs2[i] - '0') + carry;
                    carry = tmp / 10;
                    tmp = tmp % 10;
                    total3[len2 - i + len1 - j - 2] += tmp;
                }
                if(carry != 0) total3[len1 + len2 - 1 - i] += carry; // 必须考虑 len2
                carry = 0; // 对下一位进制必须清零
            }
            carry = 0;
            for(int i = 0, len = chs1.length + chs2.length + 1; i < len; i ++) {
                total3[i] += carry; // 要带着进位计算
                carry = total3[i] / 10;
                total3[i] %= 10;
            }
            StringBuilder builder = new StringBuilder();
            for(int i = 0, len = chs1.length + chs2.length + 1; i < len; i ++) {
                builder.insert(0, total3[i]);
            }
            while(builder.length() > 0 && builder.charAt(0) == '0') builder.deleteCharAt(0);
            return builder.length() == 0 ? "0" : builder.toString();
        }
    }
}
