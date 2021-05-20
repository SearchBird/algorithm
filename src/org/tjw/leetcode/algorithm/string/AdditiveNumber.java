package org.tjw.leetcode.algorithm.string;

public class AdditiveNumber {

    public static void main(String[] args) {
        Solution s = new AdditiveNumber().new Solution();
        System.out.println(s.isAdditiveNumber("11111111111011111111111"));
    }

    class Solution {
        private int index;
        public boolean isAdditiveNumber(String num) {
            if(num.length() < 3) return false;
            int maxLen = num.length() > 19 ? 18 : num.length(); // 不超出 Long 长度
            long[][] beginer = new long[(maxLen - 1) * (maxLen - 2) / 2][2];
            String[][] beginerStr = new String[(maxLen - 1) * (maxLen - 2) / 2][2];
            for(int i = 1, len1 = maxLen - 1; i < len1; i ++)
                for(int j = 1, len2 = maxLen - i; j < len2; j ++)
                    buildBegin(beginer, beginerStr, num, i, j);

            boolean success = false;
            long[] cache = new long[2];
            String[] cacheStr = new String[2];
            A:for(int i = 0, begin = 0, end = 0, len = num.length(); i < index; i ++, begin = 0, end = 0) {
                cache[0] = beginer[i][0];
                cache[1] = beginer[i][1];
                cacheStr[0] = beginerStr[i][0];
                cacheStr[1] = beginerStr[i][1];

                while(end < len) {
                    long next = cache[0] + cache[1];
                    String nextStr = String.valueOf(next);
                    int nextb = cacheStr[0].length() + cacheStr[1].length();
                    if(begin + nextb + nextStr.length() <= len &&
                       num.substring(begin + nextb, begin + nextb + nextStr.length()).equals(nextStr)) {
                        end = begin + nextb + nextStr.length();
                        begin += cacheStr[0].length();
                        cache[0] = cache[1];
                        cache[1] = next;
                        cacheStr[0] = cacheStr[1];
                        cacheStr[1] = nextStr;
                    } else continue A;
                }
                success = true;
                break;
            }
            return success;
        }

        private void buildBegin(long[][] beginer, String[][] beginerStr, String num, int first, int second) {
            String firstStr = num.substring(0, first);
            String secondStr = num.substring(first, first + second);
            if(first > 1 && firstStr.charAt(0) == '0') return;
            if(second > 1 && secondStr.charAt(0) == '0') return;
            beginer[index][0] = Long.valueOf(firstStr);
            beginer[index][1] = Long.valueOf(secondStr);
            beginerStr[index][0] = firstStr;
            beginerStr[index ++][1] = secondStr;
        }
    }
}
