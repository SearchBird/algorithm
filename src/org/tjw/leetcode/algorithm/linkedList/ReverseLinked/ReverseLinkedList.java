package org.tjw.leetcode.algorithm.linkedList.ReverseLinked;
import org.tjw.leetcode.algorithm.linkedList.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        ListNode head = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        ListNode node04 = new ListNode(5);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        head = solution.reverseList(head);
        while(head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }
    class Solution {
        private ListNode node = null;
        public ListNode reverseList(ListNode head) {
            if(head != null) {
                reverseHelper(head);
                head.next = null;
            }
            return node;
        }

        public ListNode reverseHelper(ListNode head) {
            if(head.next != null) {
                ListNode next = reverseHelper(head.next);
                next.next = head;
            } else {
                node = head;
            }

            return head;
        }
    }
}
