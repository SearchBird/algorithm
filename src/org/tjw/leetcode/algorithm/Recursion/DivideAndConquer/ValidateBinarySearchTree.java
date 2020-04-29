package org.tjw.leetcode.algorithm.Recursion.DivideAndConquer;

import java.util.List;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
        TreeNode t01 = new TreeNode(2);
        TreeNode t02 = new TreeNode(1);
        TreeNode t03 = new TreeNode(3);

        TreeNode t04 = new TreeNode(3);
        t01.left = t02;
        t01.right = t03;
        System.out.println(solution.isValidBST(t01));
    }

    class Solution {

        private TreeNode[] queueCache = new TreeNode[100000];
        private int begin = 0;
        private int end = 0;

        public boolean isValidBST(TreeNode root) {
            if(root == null) return false;
            queueCache[0] = root;
            return validate();
        }

        public boolean validate() {
            if(begin <= end) {
                TreeNode topNode = queueCache[begin ++];
                TreeNode left = topNode.left;
                TreeNode right = topNode.right;
                if(left != null) {
                    if(left.val > topNode.val){
                        return false;
                    } else {
                        queueCache[++ end] = left;
                    }
                }
                if(right != null) {
                    if(right.val < topNode.val) {
                        return false;
                    } else {
                        queueCache[++ end] = right;
                    }
                }
                return validate();
            } else {
                return true;
            }
        }
    }
}
