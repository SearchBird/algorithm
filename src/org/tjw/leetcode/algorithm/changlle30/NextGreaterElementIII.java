package org.tjw.leetcode.algorithm.changlle30;

public class NextGreaterElementIII {

    public static void main(String[] args) {
        Solution s = new NextGreaterElementIII().new Solution();
        System.out.println(s.nextGreaterElement(230241));
        System.out.println(s.nextGreaterElement(12443322));
        System.out.println(s.nextGreaterElement(1999999999));
    }

    class Solution {
        public int nextGreaterElement(int n) {
            char[] chs = String.valueOf(n).toCharArray();
            int[] nums = new int[chs.length];
            for(int i = chs.length; i -- > 0;) nums[i] = chs[i] - '0';
            for(int i = nums.length; i -- > 1;) {
                if(nums[i - 1] < nums[i]) {
                    int ti = i;
                    while(ti + 1 < nums.length && nums[i - 1] < nums[ti + 1]) ti ++;
                    nums[i - 1] ^= nums[ti];
                    nums[ti] ^= nums[i - 1];
                    nums[i - 1] ^= nums[ti];
                    for(int j = nums.length - 1; j >= i; j --) {
                        int tj = j;
                        while(tj + 1 < nums.length && nums[tj] > nums[tj + 1]) {
                            nums[tj + 1] ^= nums[tj];
                            nums[tj] ^= nums[tj + 1];
                            nums[tj + 1] ^= nums[tj ++];
                        }
                    }
                    for(int j = nums.length;j -- > 0;) chs[j] = (char)(nums[j] + '0');
                    try {
                        return Integer.valueOf(new String(chs));
                    } catch (Exception e) {
                        return -1;
                    }
                }
            }
            return -1;
        }
    }
}
