package org.tjw.leetcode.algorithm.arr101;

public class ThirdMaximumNumber {

    public static void main(String[] args) {
        Solution solution = new ThirdMaximumNumber().new Solution();

        int[] nums = new int[]{1,2};
        System.out.println(solution.thirdMax(nums));
    }

    class Solution {
        public int thirdMax(int[] nums) {
            long max = Long.MIN_VALUE;
            long temp01 = Long.MIN_VALUE;
            long temp02 = Long.MIN_VALUE;
            int i = 0;
            for(;i < nums.length;i ++) {
                int n = nums[i];
                if(max < n) {
                    temp02 = temp01;
                    temp01 = max;
                    max = nums[i];
                } else if(temp01 < n && max > n) {
                    temp02 = temp01;
                    temp01 = nums[i];
                } else if(temp02 < n && temp01 > n) {
                    temp02 = nums[i];
                }
            }
            return (temp02 == Long.MIN_VALUE || temp02 == temp01) ? (int)max : (int)temp02;
        }

        // 使用hash和压缩，不过只适合某个范围的正整数
        public int thirdMax2(int[] nums) {
            // zip hash
            int[] hashsort = new int[100000];
            int BIN = 32;

            for(int i = nums.length;i -- > 0;) {
                int temp = nums[i];
                int slot = temp / BIN;
                int pos = 1 << (temp % BIN);

                hashsort[slot] |= pos;
            }

            int count = 3;
            int max = 0;
            for(int i = hashsort.length;i -- > 0;) {
                int temp = hashsort[i];
                if(temp != 0) {
                    for(int j = 32;j -- > 0;) {
                        int pos = 1 << j;
                        if((temp & pos) != 0) {
                            if(count != 1) {
                                count --;
                                int ss = 32 * i + j;
                                if(max < ss)max = ss;
                            }
                            else return  32 * i + j;
                        }
                    }
                }
            }

            return max;
        }
    }
}
