package org.tjw.leetcode.algorithm.bfs;

import org.tjw.leetcode.algorithm.changlle30.ValidateIPAddress;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means
 * that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O'
 * on the border will be flipped to 'X'. Two cells are connected
 * if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        Solution solution = new SurroundedRegions().new Solution();

        char[][] board01 = {{'X'}};

        // 边界元素不进行遍历，并且触边情况要跳出联通块计算
        char[][] board02 = {
                {'X','O','X','X'},
                {'O','X','O','X'},
                {'X','O','X','O'},
                {'O','X','O','X'}};

        // visited已经是1的就不要遍历
        char[][] board03 = {
                {'O','X','X','O','X'},
                {'X','O','O','X','O'},
                {'X','O','X','O','X'},
                {'O','X','O','O','O'},
                {'X','X','O','X','O'}};

        // 7,6那个地方，因为判断里面一旦触边就跳出，导致四周被遍历为1，只有他自己是0就出错了
        // 改成flag判断是否触边，然后遍历所有联通块即可
        char[][] board04 = {
                {'O','X','O','O','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','X'},
                {'O','X','O','X','O','O','O','O','X'},
                {'O','O','O','O','X','O','O','O','O'},
                {'X','O','O','O','O','O','O','O','X'},
                {'X','X','O','O','X','O','X','O','X'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','O','O','X','X','O','O'}};

        // 末尾判断出错
        solution.solve(board01);
        solution.solve(board02);
        solution.solve(board03);
        solution.solve(board04);
        printf(board01);
        printf(board02);
        printf(board03);
        printf(board04);

    }

    private static void printf(char[][] board01) {
        for(int i = 0;i < board01.length;i ++) {
            for(int j = 0;j < board01[0].length;j ++) {
                System.out.print(board01[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    class Solution {

        public void solve(char[][] board) {
            // 长宽3以下的都不会被包围
            if(board == null || board.length < 3 || board[0].length < 3) return;

            // 分别拿出二维数组的xy以及 - 1的数
            int x = board.length;
            int y = board[0].length;
            int xd = x - 1;
            int yd = y - 1;
            char O = 'O';

            // 访问表
            int[][] visited = new int[x][y];

            // 用于统计陆地的队列
            int[][] lands = new int[x * y][2];

            // i j 从 1 开始都不遍历边界的
            for(int i = 1;i < xd;i ++) {
                for(int j = 1;j < yd;j ++) {
                    if(visited[i][j] != 1) {
                        // 队列头尾指针
                        // land begin
                        int lbegin = -1;
                        // land end
                        int lend = 0;
                        visited[i][j] = 1;

                        // 非边界元素遍历
                        if(board[i][j] == O)
                        {
                            lands[++ lbegin][0] = i;
                            lands[lbegin][1] = j;
                            boolean flag = true;

                            while(lend - lbegin >= 0)
                            {
                                int x1 = lands[lbegin][0];
                                int y1 = lands[lbegin ++][1];

                                // 触边警告，因为board04原因，所以触边依然要继续遍历
                                if(x1 == xd
                                        || x1 == 0
                                        || y1 == yd
                                        || y1 == 0)
                                {
                                    flag = false;
                                }

                                // 添加不越边约束
                                if(x1 + 1 < x && board[x1 + 1][y1] == O && visited[x1 + 1][y1] != 1) {
                                    lands[++ lend][0] = x1 + 1;
                                    lands[lend][1] = y1;
                                    visited[x1 + 1][y1] = 1;
                                }

                                // 只添加元素为 O 的
                                if(x1 - 1 >= 0 && board[x1 - 1][y1] == O && visited[x1 - 1][y1] != 1) {
                                    lands[++ lend][0] = x1 - 1;
                                    lands[lend][1] = y1;
                                    visited[x1 - 1][y1] = 1;
                                }
                                if(y1 + 1 < y && board[x1][y1 + 1] == O && visited[x1][y1 + 1] != 1) {
                                    lands[++ lend][0] = x1;
                                    lands[lend][1] = y1 + 1;
                                    visited[x1][y1 + 1] = 1;
                                }
                                if(y1 - 1 >= 0 && board[x1][y1 - 1] == O && visited[x1][y1 - 1] != 1) {
                                    lands[++ lend][0] = x1;
                                    lands[lend][1] = y1 - 1;
                                    visited[x1][y1 - 1] = 1;
                                }
                            }

                            // 如果已经是触边的联通块就不覆盖
                            if(flag) {
                                // 进行覆盖
                                while(lend >= 0) {
                                    int[] site2 = lands[lend --];
                                    board[site2[0]][site2[1]] = 'X';
                                }
                            }
                        }
                    }

                }
            }

            return;
        }

        // 如果不用遍历也是用dfs就会变成避开已经遍历的
        public void solve2(char[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0) return;
            int x = board.length;
            int y = board[0].length;
            int xd = x - 1;
            int yd = y - 1;
            char O = 'O';
            int[][] visited = new int[x][y];
            int ltop = -1;
            int[][] lands = new int[x * y][2];

            int top = -1;
            int[][] stack = new int[x * y][2];

            stack[++ top] = new int[]{0,0};
            A:while(top >= 0) {
                int[] site = stack[top --];
                char ele = board[site[0]][site[1]];
                visited[site[0]][site[1]] = 1;

                // 非边界元素遍历
                if(site[0] != 0
                        && site[1] != 0
                        && site[0] != xd
                        && site[1] != yd
                        && ele == O)
                {
                    int top2 = 1;
                    stack[top + top2] = site;

                    while(top2 > 0)
                    {
                        int x1 = stack[top + top2][0];
                        int y1 = stack[top + top2 --][1];
                        lands[++ ltop][0] = x1;
                        lands[ltop][1] = y1;

                        if((x1 + 1 == xd && board[x1 + 1][y1] == O) || (x1 - 1 == 0 && board[x1 - 1][y1] == O) || (y1 + 1 == yd && board[x1][y1 + 1] == O) || (y1 - 1 == 0 && board[x1][y1 - 1] == O))
                        {
                            ltop = -1;
                            break;
                        }
                        if(board[x1 + 1][y1] == O && visited[x1 + 1][y1] != 1) {
                            stack[top + ++ top2] = new int[]{x1 + 1, y1};
                            visited[x1 + 1][y1] = 1;
                        }
                        if(board[x1 - 1][y1] == O && visited[x1 - 1][y1] != 1) {
                            stack[top + ++ top2] = new int[]{x1 - 1, y1};
                            visited[x1 - 1][y1] = 1;
                        }
                        if(board[x1][y1 + 1] == O && visited[x1][y1 + 1] != 1) {
                            stack[top + ++ top2] = new int[]{x1, y1 + 1};
                            visited[x1][y1 + 1] = 1;
                        }
                        if(board[x1][y1 - 1] == O && visited[x1][y1 - 1] != 1) {
                            stack[top + ++ top2] = new int[]{x1, y1 - 1};
                            visited[x1][y1 - 1] = 1;
                        }
                    }

                    if(ltop != -1) {
                        while(ltop >= 0) {
                            int[] site2 = lands[ltop --];
                            board[site2[0]][site2[1]] = 'X';
                        }
                    }
                }

                if(site[0] + 1 < x && visited[site[0] + 1][site[1]] == 0) {
                    stack[++ top][0] = site[0] + 1;
                    stack[top][1] = site[1];
                }
                if(site[1] + 1 < y && visited[site[0]][site[1] + 1] == 0)  {
                    stack[++ top][0] = site[0];
                    stack[top][1] = site[1] + 1;
                }
            }

            return;
        }
    }
}
