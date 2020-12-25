package org.tjw.codewar.Trainning.kyu3;

public class LineSafariIsthataline {

    public static void main(String[] args) {
        Dinglemouse d = new LineSafariIsthataline().new Dinglemouse();
        System.out.println(d.line(get(new String[] {
                "           ",
                "X---------X",
                "        |  ",
                "           "}, "           ".length())));
    }
    public static char[][] get(String[] arr, int y) {
        int index = 0;
        char[][] res = new char[arr.length][y];
        for(String str : arr) res[index ++] = str.toCharArray();
        return res;
    }

    class Dinglemouse {
        int sx, sy, x, y;
        int[] ad = new int[]{-1,1};
        boolean isLine = true;
        public boolean line(final char [][] grid) {
            x = grid.length;
            y = grid[0].length;
            int count = -2, bei = 0;
            int[][] be = new int[2][2];
            boolean[][] visited = new boolean[x][y];
            for(int i = 0;i < x;i ++) // 统计路径点和始末位置
                for(int j = 0;j < y;j ++)
                    if(grid[i][j] != ' ') {
                        count ++;
                        if(grid[i][j] == 'X') {
                            if(bei == 2) return false;
                            be[bei][0] = i;
                            be[bei ++][1] = j;
                        }
                    }

            sx = be[0][0];
            sy = be[0][1];
            visited[be[0][0]][be[0][1]] = true;
            recurtion(grid, visited, count, be);
            return count == 0;
        }

        public void recurtion(char[][] grid, boolean[][] visited, int count, int[][] be) {
            if(sx != be[1][0] || sy != be[1][1]) {
                for(int i : ad) {
                    sx += i;
                    check(grid, visited);
                    sy += i;
                    check(grid, visited);
                }
                if(!isLine) return;
                if(grid[sx][sy] == 'X') return;
            }
        }

        public boolean check(char[][] grid, boolean[][] visited) {
            return sx >= 0 && sx < x && sy >= 0 && sy < y && !visited[sx][sy] && grid[sx][sy] != ' ';
        }
    }
}
