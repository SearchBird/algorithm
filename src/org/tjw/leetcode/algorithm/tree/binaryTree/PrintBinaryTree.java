package org.tjw.leetcode.algorithm.tree.binaryTree;

import org.tjw.leetcode.algorithm.Recursion.DivideAndConquer.TreeNode;
import org.tjw.leetcode.algorithm.changlle30.BinaryTreeMaximumPathSum;

import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {

    public static void main(String[] args) {
        Solution solution = new PrintBinaryTree().new Solution();
        // 问题1 出现错误答案
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(30);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(45);

        node1.right = node2;
        node2.left = node3;
        node3.right = node4;
        node4.right = node5;
        System.out.print(solution.printTree(node1));
    }

    // 因为最多10行，所以弄 2 ^ 9长度的栈刚好进行BFS
    private static TreeNode[] stack = new TreeNode[(int)Math.pow(2,9)];
    private static TreeNode[] stack2 = new TreeNode[(int)Math.pow(2,9)];
    private static int top = 0;
    private static int top2 = 0;
    // 注意这里弄成静态的空字符串，如果用list.add("")，会慢很多，明显空字符串还得创建
    private static String BLANK = "";
    class Solution {
        public List<List<String>> printTree(TreeNode root) {
            top = 0;
            if(root != null) stack[top ++] = root;
            List<List<String>> res = new ArrayList<List<String>>();

            // 把所有的 value 压倒结果集
            while(top != 0) {
                top2 = 0;
                int index = 0;
                boolean isNull = true;
                List<String> list = new ArrayList<String>();
                while(index < top) {
                    TreeNode node = stack[index ++];
                    if(node == null) {
                        list.add(BLANK);
                        stack2[top2 ++] = null;
                        stack2[top2 ++] = null;
                    } else {
                        list.add(String.valueOf(node.val));
                        stack2[top2 ++] = node.left;
                        stack2[top2 ++] = node.right;
                        isNull = false;
                    }
                }
                if(isNull) break;
                res.add(list);
                top = top2;
                TreeNode[] stack3 = stack;
                stack = stack2;
                stack2 = stack3;
            }

            // 将空格补齐
            int lev = res.size();
            int space = (int)Math.pow(2,lev) - 1;
            for(int i = lev;i -- > 0;) {
                List<String> list = res.get(i);
                for(int j = list.size() - 1;j -- > 0;)
                    for(int x = (int)Math.pow(2,lev - i) - 1;x -- > 0;)
                        list.add(j + 1, BLANK);
                for(int x = (int)Math.pow(2,lev - i - 1) - 1;x -- > 0;) {
                    list.add(0,BLANK);
                    list.add(BLANK);
                }
            }

            return res;
        }
    }
}
