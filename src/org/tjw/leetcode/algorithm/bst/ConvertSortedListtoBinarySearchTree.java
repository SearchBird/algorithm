package org.tjw.leetcode.algorithm.bst;

import org.tjw.leetcode.algorithm.Recursion.RecursionToIteration.TreeNode;
import org.tjw.leetcode.algorithm.linkedList.ListNode;

public class ConvertSortedListtoBinarySearchTree {

    public static void main(String[] args) {
        Solution s = new ConvertSortedListtoBinarySearchTree().new Solution();
        s.sortedListToBST(ListNode.buildList(new int[]{-10,-3,0,5,9}));
    }

    private static int[] arr = new int[2 * (int)Math.pow(10, 4)];
    class Solution {

        public TreeNode sortedListToBST(ListNode head) {
            int len = 0;
            ListNode node = head;
            while(node != null) {
                arr[len ++] = node.val;
                node = node.next;
            }
            return recurtion(arr, 0,  - 1, len);
        }

        public TreeNode recurtion(int[] arr, int left, int right, int len) {
            if(right < left || left >= len || right >= len) return null;
            int mid = right + left >> 1;
            TreeNode node = new TreeNode(arr[mid]);

            TreeNode l = recurtion(arr, left, mid - 1, len);
            TreeNode r = recurtion(arr, mid + 1, right, len);
            node.left = l;
            node.right = r;

            return node;
        }
    }
}
