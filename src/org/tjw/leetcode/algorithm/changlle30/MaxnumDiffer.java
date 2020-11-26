package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

public class MaxnumDiffer {

    public static void main(String[] args) {
        Solution s = new MaxnumDiffer().new Solution();

        TreeNode t1 = new TreeNode(8);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(10);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(6);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        System.out.println(s.maxAncestorDiff(t1));
    }

    private static TreeNode max = new TreeNode(-1);
    private static TreeNode min = new TreeNode(Integer.MAX_VALUE);
    class Solution {
        public int maxAncestorDiff(TreeNode root) {
            return recurtion(max, min, root, 0);
        }

        public int recurtion(TreeNode max, TreeNode min, TreeNode root, int maxVal) {
            if(root != null) {
                if(root.val > max.val) max = root;
                if(root.val < min.val) min = root;
                maxVal = Math.max(Math.abs(max.val - min.val),
                        Math.max(recurtion(max, min, root.left, maxVal), recurtion(max, min, root.right, maxVal)));
            }
            return maxVal;
        }
    }
}
