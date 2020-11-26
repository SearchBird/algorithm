package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

public class MinimumDepth {

    public static void main(String[] args) {
        Solution s = new MinimumDepth().new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println(s.minDepth(n1));
    }

    private static int begin = 0;
    private static int end = 0;
    private static TreeNode[] queue = new TreeNode[100000];
    class Solution {
        public int minDepth(TreeNode root) {
            begin = 0;
            end = 0;
            if(root != null) queue[end ++] = root;
            int min = 0;
            while(end != begin) {
                int e = end;
                for(;begin < e;) {
                    TreeNode node = queue[begin ++];
                    if(node.left == null && node.right == null) return min + 1;
                    if(node.left != null) queue[end ++] = node.left;
                    if(node.right != null) queue[end ++] = node.right;
                }
                min ++;
            }
            return min;
        }
    }
}
