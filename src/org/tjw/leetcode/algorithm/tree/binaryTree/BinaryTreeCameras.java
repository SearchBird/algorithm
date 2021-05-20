package org.tjw.leetcode.algorithm.tree.binaryTree;

import org.tjw.leetcode.algorithm.Recursion.RecursionToIteration.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeCameras {

    //// 老了，没解出来
    public static void main(String[] args) {
        Solution s = new BinaryTreeCameras().new Solution();

        System.out.println(s.minCameraCover(buildTree(buildTreeStr("0,0,null,0,0"))));
        System.out.println(s.minCameraCover(buildTree(buildTreeStr("0,0,null,0,null,0,null,null,0"))));
        System.out.println(s.minCameraCover(buildTree(buildTreeStr("0,0,null,null,0,0,null,null,0,0"))));
        System.out.println(s.minCameraCover(buildTree(buildTreeStr("0,0,0,null,0,0,null,null,0"))));
        System.out.println(s.minCameraCover(buildTree(buildTreeStr("0,null,0,null,0,null,0,null,0,0,0,null,null,0,0"))));
    }

    public static String[] buildTreeStr(String str) {
        String[] res = str.replaceAll("\\s+", "").split(",");
        for(int i = 0, len = res.length; i < len; i ++)
            if("null".equalsIgnoreCase(res[i])) res[i] = null;
        return res;
    }

    public static TreeNode buildTree(String[] arr) {
        int index = 0, len = arr.length;
        TreeNode root = new TreeNode(Integer.valueOf(arr[index ++]));
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(index < len) {
            for(int i = 0, size = queue.size(); i < size && index < len; i ++) {
                TreeNode node = queue.remove(0);
                if(node != null) {
                    String valStr = arr[index ++];
                    if(valStr == null) {
                        queue.add(null);
                    } else {
                        int val = Integer.valueOf(valStr);
                        TreeNode left = new TreeNode(val);
                        node.left = left;
                        queue.add(left);
                    }
                    if(index < len) {
                        valStr = arr[index ++];
                        if(valStr == null) {
                            queue.add(null);
                        } else {
                            int val = Integer.valueOf(valStr);
                            TreeNode right = new TreeNode(val);
                            node.right = right;
                            queue.add(right);
                        }
                    }
                }
            }
        }
        return root;
    }

    class Solution {
        private Map<TreeNode, Integer> cache = new HashMap<>();
        public int minCameraCover(TreeNode root) {
            return recurtion(root, 0);
        }

        public int recurtion(TreeNode root, int count) {
            TreeNode node = root;

            int m = Integer.MAX_VALUE, c;
            c = caluate(node, count);
            m = Math.min(m, c);

            if(root.left != null) {
                node = root.left;
                c = caluate(node, count);
                if(root.right != null) {
                    node = root.right;
                    c = recurtion(node, c);
                }
                m = Math.min(m, c);
            }


            if(root.right != null) {
                node = root.right;
                c = caluate(node, count);
                if(root.left != null) {
                    node = root.left;
                    c = recurtion(node, c);
                }
                m = Math.min(m, c);
            }

            return m;
        }

        public int caluate(TreeNode node, int c) {
            c += 1;
            if(node.left != null) {
                int c1 = recurtion(node.left, c);
                if(node.left.left != null) c = recurtion(node.left.left, c);
                if(node.left.right != null) c = recurtion(node.left.right, c);
                c = Math.min(c1, c);
            }
            if(node.right != null) {
                int c1 = recurtion(node.right, c);
                if(node.right.left != null) c = recurtion(node.right.left, c);
                if(node.right.right != null) c = recurtion(node.right.right, c);
                c = Math.min(c1, c);
            }
            return c;
        }
    }
}
