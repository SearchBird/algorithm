package org.tjw.luogu.noip;

import java.util.Scanner;

public class NOIP2014 {

    private static int[][] res = new int[][]{
            {0, 0, 1, 1, 0},
            {1, 0, 0, 1, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0}};

    public static void main(String[] args) {
        System.out.print(Integer.MAX_VALUE);
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        int sc1 = 0;
        int sc2 = 0;
        for(int i = 0;i < n1;i ++) {
            arr1[i] = sc.nextInt();
        }
        for(int i = 0;i < n2;i ++) {
            arr2[i] = sc.nextInt();
        }
        for(int i = 0;i < times;i ++) {
            int x = arr1[i % n1];
            int y = arr2[i % n2];
            sc1 += res[x][y];
            sc2 += res[y][x];
        }
        System.out.print(sc1 + " " + sc2);

    }
}
