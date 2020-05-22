package org.tjw.leetcode.algorithm.bst;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;
import org.tjw.leetcode.algorithm.changlle30.BinaryTreeMaximumPathSum;

public class KthSmallestElementinaBST {
    public static void main(String[] args) {
        Solution solution = new KthSmallestElementinaBST().new Solution();
        // 问题1 出现错误答案
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        int results = solution.kthSmallest(node1, 3);
        System.out.print(results);
    }

    class Solution {
        int k;
        int index;
        int target;
        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            recurtion(root);
            return target;
        }

        public void recurtion(TreeNode root) {
            if(root != null && index < k) {
                if(root.left != null) {
                    recurtion(root.left);
                }

                index ++;
                if(index == k) target = root.val;

                if(root.right != null) {
                    recurtion(root.right);
                }
            }
        }
    }
}
