package org.tjw.leetcode.algorithm.Recursion;

public class KthSymbolinGrammar {

    public static void main(String[] args) {
        Solution s = new KthSymbolinGrammar().new Solution();
        System.out.println(s.kthGrammar(4, 1));
        System.out.println(s.kthGrammar(4, 2));
        System.out.println(s.kthGrammar(4, 3));
        System.out.println(s.kthGrammar(4, 4));
        System.out.println(s.kthGrammar(4, 5));
        System.out.println(s.kthGrammar(4, 6));
        System.out.println(s.kthGrammar(4, 7));
        System.out.println(s.kthGrammar(4, 8));
    }


    /**
     0
     |     \
     0       1
     |  \    |  \
     0   1   1   0
     |\  |\  |\  |\
     0 1 1 0 1 0 0 1
     */
    private static boolean[] opt = new boolean[30];
    class Solution {
        public int kthGrammar(int N, int K) {
            // 用二分法反推树路径
            // left 从 1 开始, 每层长度 right = 2 ^ (N - 1)
            int mid, left = 1, right = (int)Math.pow(2, N - 1), index = 0, cur = 0;
            while(N -- > 1) { // 不用 left < right, 一定要走 N 步
                mid = left + right >> 1;
                if(K == right) opt[index ++] = true;
                else if(K > mid) {
                    left = mid;
                    opt[index ++] = true;
                }
                else if(K < mid) {
                    right = mid;
                    opt[index ++] = false;
                }
                else {
                    right = mid;
                    opt[index ++] = false;
                }
            }
            for(int i = 0;i < index;i ++)
                if(opt[i]) cur = cur == 0 ? 1 : 0;
                else cur = cur == 0 ? 0 : 1;
            return cur;
        }
    }
}
