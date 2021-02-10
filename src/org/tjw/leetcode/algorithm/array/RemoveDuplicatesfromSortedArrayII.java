package org.tjw.leetcode.algorithm.array;

public class RemoveDuplicatesfromSortedArrayII {

    public static void main(String[] args) {
        Solution s = new RemoveDuplicatesfromSortedArrayII().new Solution();
        s.removeDuplicates(new int[]{1,1,1,2,2,3});
    }

    class Solution {
        public int removeDuplicates(int[] nums) {
            int cur = 0, pre = 0, next = 1, len = nums.length;
            boolean dupFlag = false;
            while(next < len) {
                if(nums[next] == nums[pre]) {
                    next ++;
                    pre ++;
                    if(!dupFlag) {
                        nums[++ cur] = nums[pre];
                        dupFlag = true;
                    }
                } else {
                    next ++;
                    pre ++;
                    nums[++ cur] = nums[pre];
                    dupFlag = false;
                }
            }
            return cur + 1;
        }
    }
}
