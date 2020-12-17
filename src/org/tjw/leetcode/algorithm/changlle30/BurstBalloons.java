package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurstBalloons {

    public static void main(String[] args) {
        Solution s = new BurstBalloons().new Solution();
        System.out.println(s.maxCoins(new int[]{3,1,5,8}));
        System.out.println(s.maxCoins(new int[]{7,9,8,0,7,1,3,5,5,2,3,3}));
    }

    class Solution {
        private int max;
        public int maxCoins(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for(int i : nums) list.add(i);
            recurtion(list, list.size(), 0);
            return max;
        }

        public void recurtion(List<Integer> list, int len, int coins) {
            if(list.size() == 0) {
                max = Math.max(max, coins);
                return;
            }
            for(int i = 0;i < len; i ++) {
                int left = i - 1 < 0 ? 1 : list.get(i - 1);
                int right = i + 1 == len ? 1 : list.get(i + 1);
                int cur = list.remove(i);
                int temp = coins;
                coins +=  left * right * cur;
                recurtion(list, list.size(), coins);
                list.add(i, cur);
                coins = temp;
            }
        }
    }
}
