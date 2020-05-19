package org.tjw.leetcode.algorithm.slidingwindow;

public class PermutationinString {

    public static void main(String[] args) {
        Solution solution = new PermutationinString().new Solution();

        String s01 = "cbaebabacd";
        String p01 = "abc";

        String s02 = "abaacbabc";
        String p02 = "abc";

        String s03 = "acdcaeccde";
        String p03 = "c";

        String s04 = "aba";
        String p04 ="ab";

        System.out.println(solution.checkInclusion(s01, p01));
        System.out.println(solution.checkInclusion(s02, p02));
        System.out.println(solution.checkInclusion(s03, p03));
        System.out.println(solution.checkInclusion(s04, p04));
    }

    class Solution {
            public boolean checkInclusion(String s1, String s2) {
                int s1len = s1.length();
                int[] map = new int[128];
                for(int i = 0;i < s1len;i ++) {
                    map[s1.charAt(i)] ++;
                }

                int left = 0;
                int right = 0;
                int s2len = s2.length();
                char[] s2ch = s2.toCharArray();

                while(right < s2len) {
                    map[s2ch[right]] --;
                    while(map[s2ch[right]] < 0) {
                        map[s2ch[left ++]] ++;
                    }
                    right ++;
                    if(right - left == s1len) return true;
                }
                return false;
            }
    }
}
