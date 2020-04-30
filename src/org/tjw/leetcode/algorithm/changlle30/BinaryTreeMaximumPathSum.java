package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

import java.util.ArrayList;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
        // 问题1 出现错误答案
        TreeNode node1 = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        int results = solution.maxPathSum(node1);
        System.out.print(results);
    }

    class Solution {
        // max
        int m = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            recurtion(root);

            return m;
        }

        public int recurtion(TreeNode root) {
            // left sum
            int ls = 0;
            // right sum
            int rs = 0;
            if(root.left != null) ls = recurtion(root.left);
            if(root.right != null) rs = recurtion(root.right);

            // sum
            int r = root.val;
            int mm = Math.max(ls + r,Math.max(rs + r, r));

            // 要返回一个最大的mm
            m = Math.max(Math.max(m,mm),ls + rs + r);

            return mm;
        }
    }
}
