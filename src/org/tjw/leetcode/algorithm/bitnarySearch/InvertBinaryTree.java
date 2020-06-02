package org.tjw.leetcode.algorithm.bitnarySearch;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {



    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if(root == null) return root;
            List<TreeNode> stack = new ArrayList<TreeNode>();
            // 建立一个栈，然后使用广度搜索算法
            stack.add(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.remove(0);
                // 将左边改成右边，右边改成左边
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                // 把不为空的子节点加入即可
                if(node.left != null) stack.add(node.left);
                if(node.right != null) stack.add(node.right);
            }

            return root;
        }
    }
}
