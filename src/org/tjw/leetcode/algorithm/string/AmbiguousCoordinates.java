package org.tjw.leetcode.algorithm.string;

import java.util.ArrayList;
import java.util.List;

public class AmbiguousCoordinates {

    public static void main(String[] args) {
        Solution s = new AmbiguousCoordinates().new Solution();

        System.out.println(s.ambiguousCoordinates("(0123)"));
    }

    class Solution {
        public List<String> ambiguousCoordinates(String s) {
            s = s.replace("(", "").replace(")", "");
            StringBuilder appender = new StringBuilder();
            StringBuilder appender2 = new StringBuilder();
            List<String> res = new ArrayList<String>();
            for(int i = 1, len = s.length(); i < len; i ++) {
                String[] nums = new String[]{s.substring(0, i), s.substring(i, len)};

                appender.append(nums[0]);
                for(int j = 0, len0 = nums[0].length(); j < len0; j ++) {
                    if(j != 0) appender.insert(j, ".");
                    if(checkNum(appender.toString())) {
                        appender2.append(nums[1]);
                        for(int k = 0, len1 = nums[1].length(), orgLen = appender.length(); k < len1; k ++) {
                            if(k != 0) appender2.insert(k, ".");
                            if(checkNum(appender2.toString())) {
                                res.add(appender.insert(0, "(").append(", ").append(appender2).append(")").toString());
                                appender.deleteCharAt(0).setLength(orgLen);
                            }
                            if(k != 0) appender2.deleteCharAt(k);
                        }
                        appender2.setLength(0);
                    }
                    if(j != 0) appender.deleteCharAt(j);
                }
                appender.setLength(0);
            }
            return res;
        }

        public boolean checkNum(String num) {
            String[] ns = num.split("\\.");
            if(ns.length < 2 && num.indexOf(".") != -1) return false;
            if(ns[0].length() == 0) return false;
            if(ns[0].charAt(0) == '0' && ns[0].length() > 1) return false;
            if(ns.length == 2 && (ns[1].length() == 0 || ns[1].charAt(ns[1].length() - 1) == '0')) return false;
            return true;
        }
    }
}
