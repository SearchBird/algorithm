package org.tjw.leetcode.algorithm.linkedList.ReverseLinked;
import org.tjw.leetcode.algorithm.linkedList.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity
 * and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was
 * in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        Solution solution = new OddEvenLinkedList().new Solution();
        ListNode head = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        ListNode node04 = new ListNode(5);
        ListNode node05 = new ListNode(7);
        ListNode node06 = new ListNode(8);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;
        node05.next = node06;
        head = solution.oddEvenList(head);
        while(head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }
    class Solution {
        ListNode oddNode;
        ListNode evenNode;
        ListNode oddTail;
        public ListNode oddEvenList(ListNode head) {
            if(head != null) {
                reverNode(head, 1);
                if(oddTail != null) {
                    oddTail.next = evenNode;
                    head.next = oddNode;
                }
                else {
                    head.next = evenNode;
                }
            }
            return head;
        }

        public ListNode reverNode(ListNode head, int index) {
            if(head.next != null) {
                ListNode next = reverNode(head.next, ++ index);
                if(index % 2 == 1) {
                    if(oddTail == null) oddTail = next;
                    next.next = oddNode;
                    oddNode = next;
                } else {
                    next.next = evenNode;
                    evenNode = next;
                }
            }
            return head;
        }
    }
}
