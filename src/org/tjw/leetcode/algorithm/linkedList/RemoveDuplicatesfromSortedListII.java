package org.tjw.leetcode.algorithm.linkedList;

public class RemoveDuplicatesfromSortedListII {

    public static void main(String[] args) {
        Solution s = new RemoveDuplicatesfromSortedListII().new Solution();

        ListNode root = ListNode.buildList(new int[]{1,2,3,3,4,4,5});
        ListNode res = s.deleteDuplicates(root);
        while(res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
        System.out.println();

        ListNode root2 = ListNode.buildList(new int[]{1,1,2,3,3,4,4,5,6,6});
        ListNode res2 = s.deleteDuplicates(root2);
        while(res2 != null) {
            System.out.print(res2.val + " ");
            res2 = res2.next;
        }
        System.out.println();

        ListNode root3 = ListNode.buildList(new int[]{1,1});
        ListNode res3 = s.deleteDuplicates(root3);
        while(res3 != null) {
            System.out.print(res3.val + " ");
            res3 = res3.next;
        }
        System.out.println();

        ListNode root4 = ListNode.buildList(new int[]{1,1,2});
        ListNode res4 = s.deleteDuplicates(root4);
        while(res4 != null) {
            System.out.print(res4.val + " ");
            res4 = res4.next;
        }
        System.out.println();
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-101), next = dummy, pre = dummy;
            dummy.next = head;
            boolean flag = false;
            while(head != null) {
                if(head.val == pre.val) {
                    pre = head;
                    head = head.next;
                    flag = false;
                } else {
                    pre = head;
                    head = head.next;
                    if(flag) next = next.next;
                    next.next = pre;
                    flag = true;
                }
            }
            if(!flag) next.next = null;
            return dummy.next;
        }
    }
}
