package org.tjw.leetcode.algorithm.changlle30;

public class WordSearch {

    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();

        char[][] board1 = new char[][]{{'a','b'},{'c','d'}};
        System.out.println(solution.exist(board1, "cdba"));
    }

    private static int x = 0;
    private static int y = 0;
    private static int len = 0;
    private static boolean flag = false;
    class Solution {
        public boolean exist(char[][] board, String word) {
            char[] chs = word.toCharArray();
            len = chs.length - 1;
            flag = false;

            x = board.length;
            y = board[0].length;
            boolean[][] visited = new boolean[x][y];
            for(int i = 0;i < x;i ++)
                for(int j = 0;j < y;j ++) {
                    helper(board, i, j, chs, 0, visited);
                    if(flag) return true;
                }

            return flag;
        }

        public void helper(char[][] board, int i, int j, char[] chs, int index, boolean[][] visited) {
            // 如果已经找到就立即返回
            if(flag) return;
            // 将本节点浏览改为 true
            visited[i][j] = true;
            if(board[i][j] == chs[index]) {
                if(index == len) flag = true;
                index ++;
                // 找上下左右没有浏览过和没越界的元素
                if(i - 1 >= 0 && !visited[i - 1][j]) helper(board, i - 1, j, chs, index, visited);
                if(i + 1 < x && !visited[i + 1][j]) helper(board, i + 1, j, chs, index, visited);
                if(j - 1 >= 0 && !visited[i][j - 1]) helper(board, i, j - 1, chs, index, visited);
                if(j + 1 < y && !visited[i][j + 1]) helper(board, i, j + 1, chs, index, visited);
            }
            // 处理完毕，本节点浏览改为 false
            visited[i][j] = false;
        }
    }
}
