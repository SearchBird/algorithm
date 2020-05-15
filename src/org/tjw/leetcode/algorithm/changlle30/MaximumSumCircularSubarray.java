package org.tjw.leetcode.algorithm.changlle30;

import java.util.Arrays;

public class MaximumSumCircularSubarray {

    public static void main(String[] args) {
        Solution solution = new MaximumSumCircularSubarray().new Solution();

        int[] arr01 = new int[]{1,-2,3,-2};
        System.out.println(solution.maxSubarraySumCircular(arr01));
    }

    class Solution {

            public int maxSubarraySumCircular2(int[] A) {
                int[] max = new int[A.length];
                Arrays.fill(max, Integer.MIN_VALUE);                //定义max[] 用与存储当前位置 i 的左边子数组的最大和

                int sum = 0;
                int hsum = 0;
                int curmax = Integer.MIN_VALUE;
                for(int i = 0; i < A.length; i++){                  //遍历A[], 存储 i 左边子数组最大和
                    sum += A[i];
                    if(i == 0) max[i] = sum;
                    else max[i] = Math.max(sum, max[i - 1]);
                    hsum += A[i];                                   //记录最大的区段子数组的和
                    curmax = Math.max(curmax, hsum);
                    if(hsum < 0) hsum = 0;
                }

                sum = 0;
                for(int i = A.length - 1; i > 0; i--){              //自 n 到 1 遍历 A[]
                    sum += A[i];                                    //记录 i 的右边子数组和
                    curmax = Math.max(curmax, sum + max[i - 1]);    //当前右边的和与左边子数组的最大值相加（环）
                }

                return curmax;
            }


        public int maxSubarraySumCircular(int[] A) {
            // 进行序列封装
            int len = A.length;
            // max price sequence
            int[] mps = new int[len];
            Arrays.fill(mps, A[0]);

            mps[0] = A[0];
            int sum = A[0];
            // greedy sum
            int gs = Math.max(0, A[0]);
            int max = A[0];
            for(int i = 1;i < len;i ++) {
                // 进行子序列最大价值封装
                sum += A[i];
                mps[i] = Math.max(mps[i - 1], sum);

                // 另外进行贪心拿到全序列最大子序列
                gs += A[i];
                max = Math.max(max, gs);
                if(gs < 0) gs = 0;
            }

            // 01背包方法，拿最大价值序列，背包容量为1
            sum = 0;
            for(;len -- > 1;) {
                sum += A[len];
                max = Math.max(max, sum + mps[len - 1]);
            }

            return max;
        }
    }
}
