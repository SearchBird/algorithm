package org.tjw.leetcode.algorithm.changlle30;

public class SingleNumber {

    public static void main(String[] args) {
        Solution solution = new SingleNumber().new Solution();

        int[] arr01 = new int[]{1,2,1,3,2,5};
        int[] res = solution.singleNumber(arr01);
        output(res);

        // 问题2：位元上计算不恰当
        int[] arr02 = new int[]{1403617094,-490450406,-1756388866,-967931676,1878401007,1878401007,-74743538,1403617094,-1756388866,-74743538,-490450406,-1895772685};
        int[] res2 = solution.singleNumber(arr02);
        output(res2);

        // 问题3：位元列上出现三个以上重叠，应该把奇数位元定义为n个
        int[] arr03 = new int[]{-1638685546,-2084083624,-307525016,-930251592,-1638685546,1354460680,623522045,-1370026032,-307525016,-2084083624,-930251592,472570145,-1370026032,1063150409,160988123,1122167217,1145305475,472570145,623522045,1122167217,1354460680,1145305475};
        int[] res3 = solution.singleNumber(arr03);
        output(res3);

    }

    public static void output(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    class Solution {
        public int[] singleNumber(int[] nums) {
            // 长度4以上
            if(nums.length < 4) return nums;

            // 用于记录位(32位)的标记
            int site = 0;
            for(int i = 0;i < 32;i ++) {
                site = i;
                int sum = 0;
                for(int j = 0;j < nums.length;j ++) {
                    sum += (nums[j] >> i) & 1;
                }
                // 一旦发现奇数的列就跳出
                if(sum % 2 != 0) break;
            }

            // 存放刚刚记录位上的数字
            int[] targets = new int[nums.length];
            site = 1 << site;
            int tindex = 0;
            for(int i = 0;i < nums.length;i ++) {
                if((nums[i] & site) != 0) {
                    targets[tindex ++] = nums[i];
                }
            }

            // 将存放记录位上数字按照单个唯一数处理
            int t = 0;
            for(;tindex -- > 0;) {
                t ^= targets[tindex];
            }

            // 添加返回结果
            int[] res = new int[2];
            res[0] = t;

            // 排除已有结果 t，然后按照单个唯一数处理
            for(int i = 0;i < nums.length;i ++) {
                if(nums[i] == t) continue;
                res[1] ^= nums[i];
            }

            return res;
        }
    }
}
