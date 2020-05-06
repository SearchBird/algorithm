package org.tjw.leetcode.algorithm.changlle30;

public class RansomNote {


    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            if(ransomNote == null || ransomNote.length() == 0) return true;
            if(magazine == null || magazine.length() == 0) return false;

            int ml = magazine.length();
            int rl = ransomNote.length();
            char[] mc = magazine.toCharArray();
            char[] rc = ransomNote.toCharArray();
            if(ml < rl) return false;

            int[] map = new int[97 + 26];
            for(int i = 0;i < ml;i ++) {
                map[mc[i]] ++;
                if(i < rl) map[rc[i]] --;
            }

            for(int i = map.length;i -- > 0;) {
                if(map[i] < 0) return false;
            }

            return true;
        }
    }
}
