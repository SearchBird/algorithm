package org.tjw.leetcode.algorithm.changlle30;

import org.tjw.luogu.province.BellmanFord;

public class CheapestFlightsWithinKStops {


    public static void main(String[] args) {
        Solution solution = new CheapestFlightsWithinKStops().new Solution();

        int[][] test01 = new int[][]{{0,1,100},{1,2,100},{0,2,500}};

        // 0 - 1 - 3 - 4 - 5 total:170 最小
        // 0 - 2 - 4 - 5 total:230
        System.out.println(solution.findCheapestPrice(3,test01, 0, 2,1));
    }

    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            int[][] dis = new int[2][n];
            int len = flights.length;
            int index = 0;

            // 1、先初始化图，n<=100,价格<=10000，n * 价格所以就这个当最大值
            for(int i = 0;i < 2;i ++)
                for(int j = 0;j < n;j ++)
                    if(j != src) dis[i][j] = 1000000;

            // 2、进行松弛操作
            for(int i = 0;i <= K;i ++) {
                // 滚动数组的做法，需要两个指向坐标
                index ^= 1;
                int prev = index ^ 1;
                for(int j = 0;j < len;j ++) {
                    int[] edge = flights[j];
                    // 这里相对下面的进行逻辑修改一下
                    dis[index][edge[1]] = Math.min(dis[index][edge[1]],dis[prev][edge[0]] + edge[2]);
                }
            }

            return dis[index][dst] >= 1000000 ? -1 : dis[index][dst];
        }

        public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
            int[] dis = new int[n];
            int len = flights.length;


            int max = 1000000;
            for(int i = 0;i < n;i ++) if(i != src) dis[i] = max;

            for(int i = 0;i <= K;i ++) {
                int[] temp = dis.clone();
                for(int j = 0;j < len;j ++) {
                    int[] edge = flights[j];
                    temp[edge[1]] = Math.min(temp[edge[1]],dis[edge[0]] + edge[2]);
                }
                dis = temp;
            }

            return dis[dst] >= max ? -1 : dis[dst];
        }

    }


}
