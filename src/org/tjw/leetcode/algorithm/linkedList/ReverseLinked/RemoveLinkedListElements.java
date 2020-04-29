package org.tjw.leetcode.algorithm.linkedList.ReverseLinked;
import org.tjw.leetcode.algorithm.linkedList.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
        ListNode head = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        ListNode node04 = new ListNode(4);
        ListNode node05 = new ListNode(5);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;
        head = solution.removeElements(head, 4);
        while(head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }

    class Solution {
        int val;

        public ListNode removeElements(ListNode head, int val) {
            if(head != null) {
                this.val = val;
                reversehelper(head);
                if(head.val == val) head = head.next;
            }
            return head;
        }

        public ListNode reversehelper(ListNode head) {
            if(head.next != null) {
                ListNode next = reversehelper(head.next);
                if(next.val == val) {
                    head.next = next.next;
                }
            }
            return head;
        }
    }
}
