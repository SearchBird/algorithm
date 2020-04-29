package org.tjw.leetcode.algorithm.hashTable.designkey;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind
 * of duplicate subtrees, you only need to return the root node of any
 * one of them.
 *
 * Two trees are duplicate if they have the same structure with same
 * node values.
 *
 * Example 1:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * The following are two duplicate subtrees:
 *
 *       2
 *      /
 *     4
 * and
 *
 *     4
 * Therefore, you need to return above trees' root in the
 * form of a list.
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        Solution solution = new FindDuplicateSubtrees().new Solution();
        // [1,2,3,4,null,2,4,null,null,4]
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node5.left = node7;
        List<TreeNode> nodes = solution.findDuplicateSubtrees(node1);
        while(!nodes.isEmpty()) {
            System.out.print(" " + nodes.remove(0).val);
        }
    }

    class Solution {

        Map<String,Integer> subTrees = new HashMap<>();
        List<TreeNode> duplicates = new ArrayList<>();

        String split = "#";
        StringBuilder builder = new StringBuilder();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return duplicates;
        }

        private String traverse(TreeNode node){
            if(node == null)
                return "";
            String left = traverse(node.left);
            String right = traverse(node.right);
            builder.append(node.val).append(split).append(left).append(split).append(right);
            String res = builder.toString();

            subTrees.put(res, subTrees.getOrDefault(res,0)+1);
            if(subTrees.get(res) == 2)
                duplicates.add(node);
            builder.setLength(0);
            return res;
        }

        // 只能区别全是正数的二叉树, 负数不能
        // 这个算法未成功原数平方排除负数，+1 排除了0，左为1，右为2排除左右，
        // 乘以层级level达成hash值
        public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
            HashMap<Long, TreeNode> map = new HashMap<Long, TreeNode>();
            HashSet<TreeNode> result = new HashSet<TreeNode>();
            if(root == null) return new ArrayList<>(result);
            ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();

            nodeList.add(root);
            while(!nodeList.isEmpty()) {
                TreeNode node = nodeList.remove(0);
                if(node.left != null) nodeList.add(node.left);
                if(node.right != null) nodeList.add(node.right);

                int lev = 1;
                long hash = 0;
                ArrayList<TreeNode> hashList = new ArrayList<TreeNode>();
                ArrayList<Integer> intList = new ArrayList<Integer>();
                hashList.add(node);
                intList.add(1);

                while(!hashList.isEmpty()) {
                    TreeNode temp = hashList.remove(0);
                    if(temp.left != null) {
                        hashList.add(temp.left);
                        intList.add(1);
                    }
                    if(temp.right != null) {
                        hashList.add(temp.right);
                        intList.add(2);
                    }
                    hash += (temp.val * temp.val + 1) * lev * intList.remove(0);
                    lev ++;
                }
                if(map.containsKey(hash)) {
                    result.add(map.get(hash));
                } else {
                    map.put(hash, node);
                }
            }

            return new ArrayList<>(result);
        }
    }
}
