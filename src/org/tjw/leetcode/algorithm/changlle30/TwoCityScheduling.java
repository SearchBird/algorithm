package org.tjw.leetcode.algorithm.changlle30;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有2N个人，分配给两城N个人，要求路费最小
 */
public class TwoCityScheduling {

    public static void main(String[] args) {
        TwoCityScheduling scheduling = new TwoCityScheduling();

        int[][] arr = new int[][] {{1,2},{1,2},{11,22},{11,22}};
        int[][] arr2 = new int[][] {{22,2},{21,2},{11,22},{11,22}};

        System.out.println(scheduling.scheduling(arr));
        System.out.println(scheduling.scheduling(arr2));
        System.out.println();
    }

    /**
     * 用差值排序，平时都是直接一维排序，二维的话可以进行一个差值排序
     * @param costs
     * @return
     */
    public int scheduling(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - o2[0] + o2[1];
            }
        });

        int N = costs.length >> 1;
        int cost = 0;
        for(int i = costs.length;i -- > 0;) {
            cost += i >= N ? costs[i][1] : costs[i][0];
        }
        return cost;
    }
}
