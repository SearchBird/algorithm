package org.tjw.leetcode.algorithm.linkedList;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 * begin to intersect at node c1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 *
 * Example 2:
 *
 *
 * Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Reference of the node with value = 2
 * Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 *
 *
 * Example 3:
 *
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: null
 * Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionofTwoLinkedLists {

    public static void main(String[] args) {
        Solution solution = new IntersectionofTwoLinkedLists().new Solution();
        ListNode head = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        ListNode node04 = new ListNode(5);
        head.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        ListNode head2 = new ListNode(1);
        ListNode node05 = new ListNode(2);
        ListNode node06 = new ListNode(2);
        head2.next = node05;
        node05.next = node06;
        node06.next = node01;
        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        System.out.println(solution.getIntersectionNode(head, head2));
    }

    class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if(headA == null || headB == null) return null;
            ListNode runner01 = headA;
            ListNode runner02 = headB;

            while(true) {
                while(runner01.next != null) {
                    if(runner01 == runner02) return runner01;
                    runner01 = runner01.next;
                }

                if(runner01 == runner02) return runner01;
                runner01 = headA;

                if(runner02.next == null) return null;
                runner02 = runner02.next;
            }
        }
    }
}
