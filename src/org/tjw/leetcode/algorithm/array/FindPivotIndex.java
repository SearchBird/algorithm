package org.tjw.leetcode.algorithm.array;

public class FindPivotIndex {
    public static void main(String[] args) {
        Solution solution = new FindPivotIndex().new Solution();

        int[] arr = new int[]{-1,-1,-1,0,1,1};
        int[] arr2 = new int[]{-1,-1,0,1,1,0};
        int index = solution.pivotIndex(arr);
        int index2 = solution.pivotIndex(arr2);
        if(index != -1)
            System.out.println(index + " : " + arr[index]);
        if(index2 != -1)
            System.out.println(index2 + " : " + arr[index2]);
    }

    class Solution {
        /**
         * 优化, 逐个推
         * @param nums
         * @return
         */
        public int pivotIndex(int[] nums) {
            if(nums.length==0) return -1;
            int suml=0,sumr=0;
            for(int i=1;i<nums.length;i++){
                sumr+=nums[i];
            }
            if(sumr==suml) return 0;
            for(int i=1;i<nums.length;i++){
                sumr=sumr-nums[i];
                suml = suml + nums[i-1];
                if(suml==sumr) return i;
            }
            return -1;
        }
        /**
         * 有时候问题总会简单点, 但不知道为什么总会自己弄复杂
         * @param nums
         * @return
         */
        public int pivotIndex02(int[] nums) {
            int len = nums.length;
            if(len == 0) return -1;
            for(int i = 0;i < len;i ++) {
                int leftV = 0;
                int rightV = 0;
                for(int left = 0;left < i;left ++) {
                    leftV += nums[left];
                }
                for(int right = i + 1;right < len;right ++) {
                    rightV += nums[right];
                }
                if(leftV == rightV) return i;
            }
            return -1;
        }
    }
}
