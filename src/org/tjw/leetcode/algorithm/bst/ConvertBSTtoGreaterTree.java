package org.tjw.leetcode.algorithm.bst;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;
import org.tjw.leetcode.algorithm.changlle30.PathWithMinimumEffort;

import java.text.ParseException;
import java.util.ArrayList;

public class ConvertBSTtoGreaterTree {
    public static void main(String[] args) throws ParseException {
        TreeNode root1 = new TreeNode(4);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(-1);
        TreeNode root4 = new TreeNode(-3);
        TreeNode root5 = new TreeNode(0);

        root1.left = root2;
        root2.left = root4;
        root4.right = root3;
        root3.right = root5;

        Solution s = new ConvertBSTtoGreaterTree().new Solution();
        s.convertBST(root1);
    }
    class Solution {
        public TreeNode convertBST(TreeNode root) {
            if(root != null) {
                // bfs
                int total = bfs(root);
                // dfs
                dfs(root, total);
            }
            return root;
        }

        public int bfs(TreeNode root) {
            return root == null ? 0 : bfs(root.left) + bfs(root.right) + root.val;
        }

        public int dfs(TreeNode root, int total) {
            if(root.left != null) total = dfs(root.left, total);
            int temp = total - root.val;
            root.val = total;
            if(root.right != null) temp = dfs(root.right, temp);
            return temp;
        }
    }
}
