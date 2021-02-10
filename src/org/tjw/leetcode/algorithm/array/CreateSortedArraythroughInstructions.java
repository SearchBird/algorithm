package org.tjw.leetcode.algorithm.array;

import java.util.Arrays;

public class CreateSortedArraythroughInstructions {

    public static void main(String[] args) {
        Solution s = new CreateSortedArraythroughInstructions().new Solution();
        System.out.println(s.createSortedArray(new int[]{1,5,6,2}));
        System.out.println(s.createSortedArray(new int[]{1,2,3,6,5,4}));
        System.out.println(s.createSortedArray(new int[]{1,3,3,3,2,4,2,1,2}));
        System.out.println(s.createSortedArray(new int[]{4,14,10,2,5,3,8,19,7,20,12,1,9,15,13,11,18,6,16,17}));
    }

    private static int N = 100001;
    private static int[] tree = new int[N];
    class Solution {

        public int lowbit(int i) {
            return i & -i;
        }

        public void update(int i, int v) {
            while(i <= N) {
                tree[i] += v;
                i += lowbit(i);
            }
        }

        public int getSum(int i) {
            int ans = 0;
            while(i > 0) {
                ans += tree[i];
                i -= lowbit(i);
            }
            return ans;
        }

        public int createSortedArray(int[] inst) {
            long res = 0;
            int len = inst.length;
            Arrays.fill(tree, 0);
            for(int i = 0;i < len;i ++) {
                res += Math.min(getSum(inst[i] - 1), i - getSum(inst[i]));
                update(inst[i], 1);
            }
            return (int)(res % ((int)Math.pow(10, 9) + 7));
        }
    }
}
