package org.tjw.leetcode.algorithm.bst;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;
import org.tjw.leetcode.algorithm.changlle30.PathWithMinimumEffort;

import java.text.ParseException;

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
                if(root.right != null) {
                    convertBST(root.right);
                    if(root.right.left != null) root.val += root.right.left.val;
                    else root.val += root.right.val;
                }

                if(root.left != null) {
                    convertBST(root.left);
                    TreeNode temp = root.left;
                    while(temp != null) {
                        temp.val += root.val;
                        temp = temp.right;
                    }
                    if(root.left.left != null) root.left.left.val += root.val;
                }

            }
            return root;
        }
    }
}
