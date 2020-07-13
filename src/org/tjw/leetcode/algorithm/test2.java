package org.tjw.leetcode.algorithm;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test2 {

    public static void main(String[] args) throws Exception {
        Solution s = new test2().new Solution();

        TreeNode t01 = new TreeNode(1);
        TreeNode t02 = new TreeNode(0);
        TreeNode t03 = new TreeNode(2);

        t01.left = t02;
        t01.right = t03;
        TreeNode t1 = s.trimBST(t01, 1, 2);
        return;

    }
    class Solution {
        public TreeNode trimBST(TreeNode root, int L, int R) {
            while(root.val < L) root = root.right;

            TreeNode nodeL = root;
            TreeNode NL = nodeL;
            while(NL.val != L) {
                if(NL.val > L) NL = NL.left;
                else {
                    NL = NL.right;
                    nodeL = NL;
                }
            }
            NL.left = null;
            if(root.val != nodeL.val)root.left = nodeL;

            TreeNode nodeR = root;
            while(nodeR.val < R) nodeR = nodeR.right;
            nodeR.right = null;

            return root;
        }
    }
}
