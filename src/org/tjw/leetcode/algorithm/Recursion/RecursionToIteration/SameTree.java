package org.tjw.leetcode.algorithm.Recursion.RecursionToIteration;

import java.util.Vector;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they
 * are structurally identical and the nodes have the same value.
 *
 * Example 1:
 *
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 * Example 2:
 *
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * Output: false
 * Example 3:
 *
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 */
public class SameTree {

    public static void main(String[] args) {
        Solution solution = new SameTree().new Solution();
        TreeNode t0 = tree0();
        TreeNode t00 = tree00();
        System.out.println(solution.isSameTree(t0, t00));
    }

    class Solution {
        private Vector<TreeNode> vcp = new Vector<>();
        private Vector<TreeNode> vcq = new Vector<>();

        public boolean isSameTree(TreeNode p, TreeNode q) {
            vcp.add(p);
            vcq.add(q);

            while(vcp.size() != 0 && vcq.size() != 0) {

                TreeNode p0 = vcp.remove(0);
                TreeNode q0 = vcq.remove(0);

                if(p0 == q0) continue;
                else if(p0 != null && q0 != null) {
                    if(p0.val != q0.val)
                        return false;
                } else return false;

                vcp.add(p0.left);
                vcp.add(p0.right);

                vcq.add(q0.left);
                vcq.add(q0.right);
            }

            return true;
        }

    }

    public static TreeNode tree0() {
        TreeNode t0 = new TreeNode(1);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);

        t0.left = t1;
        t0.right = t2;
        return t0;
    }
    public static TreeNode tree00() {
        TreeNode t00 = new TreeNode(1);
        TreeNode t01 = new TreeNode(11);
        TreeNode t02 = new TreeNode(1);

        t00.left = t01;
        t00.right = t02;
        return t00;
    }
}
