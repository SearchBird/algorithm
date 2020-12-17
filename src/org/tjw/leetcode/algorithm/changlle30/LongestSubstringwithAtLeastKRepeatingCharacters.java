package org.tjw.leetcode.algorithm.changlle30;

public class LongestSubstringwithAtLeastKRepeatingCharacters {

    public static void main(String[] args) {
        Solution s = new LongestSubstringwithAtLeastKRepeatingCharacters().new Solution();

        System.out.println(s.longestSubstring("aaabb", 3));
        System.out.println(s.longestSubstring("ababbc", 2));
        System.out.println(s.longestSubstring("aaabbb", 3));
        System.out.println(s.longestSubstring("ababacb", 3));
    }

    class Solution {
        public int longestSubstring(String s, int k) {
            if(k < 2) return s.length();
            int len = s.length(), total = 0, temp = 0;
            int[] fre = new int[len];
            int[] totals = new int[26];
            char[] chs = s.toCharArray();
            for(char ch : chs)
                totals[ch - 'a'] ++;

            for(int i = 0;i < len;i ++)
                fre[i] = totals[chs[i] - 'a'];

            boolean zeroF = false;
            while(true) {

                for(int i = 0;i < len;i ++)
                    if(fre[i] != 0 && fre[i] < k) {
                        zeroF = true;
                        fre[i] = 0;
                    }

                if(!zeroF) break;

                for(int i = 0;i < len;i ++) {
                    if(fre[i] == 0) {
                        reclculate(i + 1, len, fre, chs);
                    } else if(i == 0) reclculate(0, len, fre, chs);
                }


                zeroF = false;
            }

            for(int i = 0;i < len;i ++) {
                if(fre[i] == 0) {
                    total = Math.max(temp, total);
                    temp = 0;
                } else
                    temp ++;
            }
            return Math.max(temp, total);
        }

        private void reclculate(int i, int len, int[] fre, char[] chs) {
            if(i < len && fre[i] != 0) {
                int[] totals = new int[26];
                for(int j = i;j < len;j ++) {
                    if(fre[j] == 0) {
                        len = j;
                        break;
                    }
                    totals[chs[j] - 'a'] ++;
                }
                for(int j = i;j < len;j ++) {
                    fre[j] = totals[chs[j] - 'a'];
                }
            }
        }
    }
}
