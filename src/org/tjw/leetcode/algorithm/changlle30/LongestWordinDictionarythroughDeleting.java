package org.tjw.leetcode.algorithm.changlle30;

import java.util.*;

public class LongestWordinDictionarythroughDeleting {

    public static void main(String[] args) {
        Solution s = new LongestWordinDictionarythroughDeleting().new Solution();
        String res = s.findLongestWord("aewfafwafjlwajflwajflwafj", Arrays.asList("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"));
        System.out.println(res);
    }

    class Solution {
        public String findLongestWord(String s, List<String> d) {
            String res = "";
            char[] chs = s.toCharArray();
            int len = s.length(), len2 = d.size();
            Map<Character, ArrayList<Integer>> cache = new HashMap<Character, ArrayList<Integer>>();

            for(int i = 0; i < len; i ++) {
                ArrayList<Integer> list = cache.getOrDefault(chs[i], new ArrayList<Integer>());
                list.add(i);
                cache.put(chs[i], list);
            }

            int[] indexs = new int[26];
            A:for(int i = 0; i < len2; i ++) {
                char[] temp = d.get(i).toCharArray();
                int tempLen = temp.length, index = -1;
                Arrays.fill(indexs, 0);

                B:for(int j = 0; j < tempLen; j ++) {
                    char ch = temp[j];
                    if(cache.containsKey(ch)) {
                        ArrayList<Integer> list = cache.get(ch);
                        int chIndex = ch - 'a';
                        int ii = indexs[chIndex];
                        while(ii < list.size()) {
                            int ii2 = list.get(ii);
                            if(index < ii2) {
                                index = ii2; continue B;
                            }
                            ii = ++ indexs[chIndex];
                        }
                    }
                    continue A;
                }

                if(temp.length > res.length()
                        ||(temp.length == res.length() && d.get(i).compareTo(res) < 0)) res = d.get(i);
            }
            return res;
        }
    }
}
