package org.tjw.leetcode.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsinaString {

    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsinaString().new Solution();

        String s01 = "cbaebabacd";
        String p01 = "abc";

        String s02 = "abaacbabc";
        String p02 = "abc";

        String s03 = "acdcaeccde";
        String p03 = "c";

        String s04 = "aba";
        String p04 ="ab";

        System.out.println(solution.findAnagrams(s01, p01));
        System.out.println(solution.findAnagrams(s02, p02));
        System.out.println(solution.findAnagrams(s03, p03));
        System.out.println(solution.findAnagrams(s04, p04));
    }

    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            int left=0;
            int right=0;

            int[] counts = new int[128];
            for(char x: p.toCharArray()){
                counts[x]++;
            }
            while(right<s.length()){
                counts[s.charAt(right)]--;
                while(counts[s.charAt(right)]<0){
                    counts[s.charAt(left++)]++;
                }
                right++;
                if(right-left==p.length())res.add(left);
            }
            return res;
        }





        public List<Integer> findAnagrams2(String s, String p) {
            int len = s.length();
            char[] sc = s.toCharArray();

            int plen = p.length();
            char[] pc = p.toCharArray();
            int[] map = new int[26];
            List<Integer> findList = new ArrayList<Integer>();

            // 放hashmap
            for(int i = 0;i < plen;i ++) {
                map[pc[i] - 'a'] ++;
            }


            for(int i = 0;i < len;i ++) {
                if(p.indexOf(sc[i]) != -1) {
                    int[] temp = Arrays.copyOf(map, 26);
                    int index = 0;

                    for(int j = i;j < len;j ++) {
                        int ji = sc[j] - 'a';
                        if(temp[ji] == 0) break;
                        temp[ji] --;
                        index ++;
                        // 外面还有一重
                        if(index == plen) {
                            findList.add(i);
                        }
                    }
                }
            }

            return findList;
        }
    }
}
