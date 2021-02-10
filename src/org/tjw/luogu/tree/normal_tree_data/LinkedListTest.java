package org.tjw.luogu.tree.normal_tree_data;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LinkedListTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList<>();
        int n = sc.nextInt();
        while(n -- > 0) {
            int opt = sc.nextInt();
            int val = sc.nextInt();
            switch (opt) {
                case 1 : list.addLast(val);break;
                case 2 : list.remove(new Integer(val));break;
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);
    }
}
