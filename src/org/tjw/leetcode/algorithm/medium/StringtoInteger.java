package org.tjw.leetcode.algorithm.medium;

public class StringtoInteger {

    public static void main(String[] args) {
        Solution solution = new StringtoInteger().new Solution();
        // 数字末尾的不用管
        String str01 = "2147483649 aaafdsewrwe"; // 2147483647

        // 超过int的只返回Integer.MAX_VALUE或Integer.MIN_VALUE
        String str02 = "-6147483648"; // -2147483648

        // +符号也要考虑
        String str03 = "     +004500"; // 4500

        // 双符号位认为不正确
        String str04 = "-+2"; // 0
        String str05 = "+-2"; // 0

        // 前面空格应该处理中去掉
        String str06 = "   -2"; // -2

        // 符号位后出现0不要进行添加
        String str07 = "  -0012a42"; // -12


        // 符号位或其他字符中间隔断，只取前面的数字
        String str08 = "0-6147483648"; // 0
        String str09 = "9-6147483648"; // 9

        // 开头0也不管
        String str10 = "00008"; // 8

        // 开头0和空格也不管
        String str11 = "  0000000000012345678"; // 12345678

        // 开头不是符号位或者数字，返回0
        String str12 = "  d10000000000012345678"; // 0

        System.out.println(solution.myAtoi(str01));
        System.out.println(solution.myAtoi(str02));
        System.out.println(solution.myAtoi(str03));
        System.out.println(solution.myAtoi(str04));
        System.out.println(solution.myAtoi(str05));
        System.out.println(solution.myAtoi(str06));
        System.out.println(solution.myAtoi(str07));
        System.out.println(solution.myAtoi(str08));
        System.out.println(solution.myAtoi(str09));
        System.out.println(solution.myAtoi(str10));
        System.out.println(solution.myAtoi(str11));
        System.out.println(solution.myAtoi(str12));
    }


    class Solution {
        public int myAtoi(String str) {
            char[] chs = str.toCharArray();
            if(chs == null || chs.length == 0) return 0;

            // 创建一个一样长度的暂存数组
            char[] nums = new char[chs.length];
            int len = 0;

            // 负数 negative
            boolean ne = false;
            // 连续序列 continuous sequence
            boolean cs = false;
            // 找到首数字 find number
            boolean fn = false;
            // 首个符号 first sign
            boolean fs = false;
            for(int i = trim(chs);i < chs.length;i ++) {
                char ch = chs[i];
                if(!fn && !cs && !fs && (ch == '-' || ch == '+')) {
                    if(ch == '-') ne = true;
                    fs = true;
                    continue;
                }
                int n = ch - '0';
                if(n < 0 || n > 9) {
                    if(cs) break;
                    if(!cs || fn || fs) return 0;
                    continue;
                } else {
                    fn = true;
                    if(!cs && n == 0) {
                        continue;
                    }
                    cs = true;
                    nums[len ++] = ch;
                }
            }

            if(len > 10) return ne ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            long res = 0;

            for(int i = 0;i < len;i ++) {
                long n = nums[i] - '0';
                int tens = (int)Math.pow(10,len - 1 - i);
                // 进行10进位计算
                res += n * tens;
            }
            if(ne) res = - res;
            if(res <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if(res >= Integer.MAX_VALUE) return Integer.MAX_VALUE;

            return (int)res;
        }

        /**
         * 去掉开头空格
         * @param chs
         * @return
         */
        public int trim(char[] chs) {
            for(int i = 0;i < chs.length;i ++)
                if(chs[i] != ' ') return i;
            return 0;
        }
    }
}
