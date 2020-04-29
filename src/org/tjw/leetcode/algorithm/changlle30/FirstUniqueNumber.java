package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用链表思想处理
 */
public class FirstUniqueNumber {
    public static void main(String[] args) {

        int[] arr = new int[]{2,3,4};
        FirstUnique solution = new FirstUniqueNumber().new FirstUnique(arr);

        System.out.println(solution.showFirstUnique());
        solution.add(2);
        solution.add(4);
        System.out.println(solution.showFirstUnique());
        solution.add(3);
        System.out.println(solution.showFirstUnique());
        System.out.println(solution.showFirstUnique());
        solution.add(1);
        System.out.println(solution.showFirstUnique());
    }

    // 双链表思想
    class FirstUnique {

        Node empty = new Node(-1);
        Node head = empty;
        Node tail = empty;
        public HashMap<Integer, Integer> map = new HashMap<>();

        public FirstUnique(int[] nums) {
            for(int i = 0;i < nums.length;i ++) {
                add(nums[i]);
            }
        }

        public int showFirstUnique() {
            return head.val;
        }

        public void add(int val) {
            Node node = new Node(val);
            tail.next = node;
            tail = tail.next;

            if (map.containsKey(val)) {
                // 判断存在就设置为1
                map.put(val, 1);
                moveToHead();
            } else {
                if(head.val == -1) head = node;;
                map.put(val, 0);
            }
        }

        public void moveToHead() {
            if(head.val != tail.val) return;
            while(head.next != null) {
                head = head.next;
                // 跟着链表找到第一个为0的节点
                if(map.get(head.val) == 0) return;
            }
            head = empty;
        }

    }

    class Node {
        Node next;
        int val;
        Node(int val) {this.val = val;}
    }

    // 改为单链表思想即可
    class FirstUnique2 {

        int head = -1;
        int headi = -1;
        public HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<Integer>();

        public FirstUnique2(int[] nums) {
            for(int i = 0;i < nums.length;i ++) {
                add(nums[i]);
            }
        }

        public int showFirstUnique() {
            return head;
        }

        public void add(int val) {
            list.add(val);


            if (map.containsKey(val) ) {
                if( map.get(val) != 1) {
                    // 判断存在就设置为1
                    map.put(val, 1);
                    moveToHead(val);
                }
            } else {
                if(head == -1) {
                    head = val;
                    headi = list.size() - 1;
                }
                map.put(val, 0);
            }
        }

        public void moveToHead(int val) {
            if(head != val) return;
            int len = list.size();
            for(int i = headi + 1;i < len;i ++) {
                int temp = list.get(i);
                // 跟着链表找到第一个为0的节点
                if(map.get(temp) == 0) {
                    head = temp;
                    headi = i;
                    return;
                }
            }
            head = -1;
        }

    }
}
