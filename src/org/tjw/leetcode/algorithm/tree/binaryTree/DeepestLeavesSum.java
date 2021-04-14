package org.tjw.leetcode.algorithm.tree.binaryTree;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

public class DeepestLeavesSum {

    public static void main(String[] args) {
        Solution s = new DeepestLeavesSum().new Solution();
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r6;
        r3.left = r5;
        r3.right = r7;
        int res = s.deepestLeavesSum(r1);

        System.out.print(res);
    }

    class Solution {

        private int deep = 0, maxDeep = 0, total = 0;

        public int deepestLeavesSum(TreeNode root) {
            if(root == null) return 0;
            deep ++;
            if(deep > maxDeep) {
                maxDeep = deep;
                total = 0;
            }
            if(root.left != null) {
                deepestLeavesSum(root.left);
                deep --;
            }
            if(root.right != null) {
                deepestLeavesSum(root.right);
                deep --;
            }
            if(deep == maxDeep) total += root.val;
            return total;
        }


        // private static TreeNode[][] stack = new TreeNode[2][2500];
        // public int deepestLeavesSum(TreeNode root) {
        //     int pre = 0, cur = 1, preIdx = 0, curIdx = 0;
        //     if(root != null) {
        //         stack[pre][preIdx ++] = root;
        //     }
        //     while(preIdx != 0) {
        //         for(int i = 0; i < preIdx; i ++) {
        //             TreeNode node = stack[pre][i];
        //             if(node.left != null) stack[cur][curIdx ++] = node.left;
        //             if(node.right != null) stack[cur][curIdx ++] = node.right;
        //         }
        //         if(curIdx == 0) {
        //             int total = 0;
        //             for(int i = 0; i < preIdx; i ++) total += stack[pre][i].val;
        //             return total;
        //         }
        //         pre ^= 1;
        //         cur ^= 1;
        //         preIdx = curIdx;
        //         curIdx = 0;
        //     }
        //     return 0;
        // }
    }
}
