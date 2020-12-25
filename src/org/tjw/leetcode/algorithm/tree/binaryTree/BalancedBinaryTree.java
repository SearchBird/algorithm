package org.tjw.leetcode.algorithm.tree.binaryTree;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

public class BalancedBinaryTree {

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(9);
        TreeNode r3 = new TreeNode(20);
        TreeNode r4 = new TreeNode(15);
        TreeNode r5 = new TreeNode(7);

        r1.left = r2;
        r1.right = r3;
        r3.left = r4;
        r3.right = r5;

        Solution s = new BalancedBinaryTree().new Solution();
        s.isBalanced(r1);
    }

    class Solution {
        boolean noBalanced = false;
        public boolean isBalanced(TreeNode root) {
            recurtion(root);
            return !noBalanced;
        }
        public int recurtion(TreeNode root) {
            if(noBalanced || root == null) return 0;
            int left = recurtion(root.left);
            int right = recurtion(root.right);
            if(Math.abs(left - right) > 1) {
                noBalanced = true; return Integer.MAX_VALUE;
            }
            return Math.max(left, right) + 1;
        }
    }
}
