package org.tjw.leetcode.algorithm.linkedList.ReverseLinked;
import org.tjw.leetcode.algorithm.linkedList.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList  {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();

        int[] arr = new int[]{-16557,-8725,-29125,28873,-21702,15483,-28441,-17845,
                -4317,-10914,-10914,-4317,-17845,-28441,15483,-21702,28873,
                -29125,-8725,-16557};

        ListNode head = null;
        ListNode next = null;
        for(int i = 0;i < arr.length;i ++) {
            ListNode node = new ListNode(arr[i]);
            if(head == null) {
                head = node;
                next = node;
            } else {
                next.next = node;
                next = node;
            }
        }
        System.out.print(solution.isPalindrome(head));
    }

    class Solution {
        int[] stack;
        int top = -1;
        int ttop = 0;
        int len = 0;
        boolean isOdd = false;
        int index = 0;
        boolean isPali = true;
        public boolean isPalindrome(ListNode head) {
            if(head != null && head.next != null) {
                reverList(head);
                if(head.val != stack[0]) isPali = false;
            }
            return isPali;
        }

        public ListNode reverList(ListNode head) {
            if(head != null) {
                index ++;
                ListNode next = reverList(head.next);
                if(next == null) return head;
                if(!isPali) return null;
                if(stack == null) init();

                if(top < len - 1) {
                    stack[++ top] = next.val;
                    ttop = top;
                } else {
                    if(isOdd) {
                        isOdd = false;
                        ttop --;
                    }
                    if(next.val != stack[ttop --]) isPali = false;
                }
            }
            return head;
        }

        public void init() {
            isOdd = index % 2 == 1 ? true : false;
            if(isOdd) {
                len = (index >> 1) + 1;
            } else {
                len = index >> 1;
            }
            stack = new int[len];
        }
    }
}
